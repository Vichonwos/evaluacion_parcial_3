package com.hospital_vm_cl.service;

import com.hospital_vm_cl.dto.PacienteDTO;
import com.hospital_vm_cl.model.Paciente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargaMasivaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void guardarMasivo(List<PacienteDTO> lista) {

        int batchSize = 50;

        for (int i = 0; i < lista.size(); i++) {

            PacienteDTO dto = lista.get(i);

            Paciente p = new Paciente();
            p.setRun(dto.getRun());
            p.setNombre(dto.getNombre());
            p.setApellido(dto.getApellido());
            p.setCorreo(dto.getCorreo());
            p.setFechaNacimiento(dto.getFechaNacimiento());

            entityManager.persist(p);

            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            }
        }
    }
