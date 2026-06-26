package com.hospital_vm_cl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.hospital_vm_cl.model.User;
import com.hospital_vm_cl.repository.UserRepository;


    @Service
public class UserService {


    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElseThrow();
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}

