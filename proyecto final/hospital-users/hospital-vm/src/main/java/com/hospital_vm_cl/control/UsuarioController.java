package com.hospital_vm_cl.controller;

import com.hospital_vm_cl.model.Usuario;
import com.hospital_vm_cl.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {

        return repository.save(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {

        return repository.findAll();
    }
}