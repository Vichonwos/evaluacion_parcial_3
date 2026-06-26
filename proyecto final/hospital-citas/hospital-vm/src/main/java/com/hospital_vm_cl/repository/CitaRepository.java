package com.hospital_vm_cl.repository;

import com.hospital_vm_cl.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitaRepository extends JpaRepository<Cita, Long> {
}