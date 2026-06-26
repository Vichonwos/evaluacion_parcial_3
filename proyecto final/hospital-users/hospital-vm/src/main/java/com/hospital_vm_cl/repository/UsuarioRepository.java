package com.hospital_vm_cl.repository;

import com.hospital_vm_cl.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository
        extends JpaRepository<Usuario, Integer> {
}
