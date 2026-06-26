package com.hospital_vm_cl.repository;

import com.hospital_vm_cl.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
