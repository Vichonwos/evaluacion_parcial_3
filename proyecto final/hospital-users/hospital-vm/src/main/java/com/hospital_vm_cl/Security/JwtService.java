package com.hospital_vm_cl.Security;

import org.springframework.stereotype.Service;

@Service
public class JwtService {

    public String generateToken(String username) {
        return username + "-token";
    }

    public String extractUsername(String token) {

        if (token == null) {
            return null;
        }

        return token.replace("-token", "");
    }
}