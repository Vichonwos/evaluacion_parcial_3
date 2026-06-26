package com.hospital_vm_cl.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        System.out.println(">>> RUTA RECIBIDA: " + path);
        // RUTAS PÚBLICAS
        if (
                path.startsWith("/auth")
                        || path.startsWith("/api/v1/pacientes")
        ) {
            System.out.println(">>> RUTA PÚBLICA, dejando pasar");
            chain.doFilter(request, response);
            return;
        }


        String header = request.getHeader("Authorization");
        System.out.println(">>> HEADER: " + header);
        System.out.println(">>> PATH: " + path);
        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);

            String username = jwtService.extractUsername(token);

            if (
                    username != null
                            && SecurityContextHolder.getContext()
                            .getAuthentication() == null
            ) {

                UserDetails user =
                        userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(auth);
            }
        }

        chain.doFilter(request, response);
    }
}