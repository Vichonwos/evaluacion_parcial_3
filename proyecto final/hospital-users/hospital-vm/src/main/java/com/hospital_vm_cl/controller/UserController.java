package com.hospital_vm_cl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return "Usuario con ID: " + id;
    }
}
