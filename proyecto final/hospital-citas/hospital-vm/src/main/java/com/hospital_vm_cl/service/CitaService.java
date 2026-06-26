package com.hospital_vm_cl.service;

import com.hospital_vm_cl.model.Cita;
import com.hospital_vm_cl.repository.CitaRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CitaService {

    private final CitaRepository repository;
    private final RestTemplate restTemplate;

    public CitaService(CitaRepository repository,
                       RestTemplate restTemplate) {

        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public Cita guardar(Cita cita) {

        String url =
                "http://localhost:8081/api/v1/pacientes/"
                        + cita.getPacienteId();

        Object paciente =
                restTemplate.getForObject(url, Object.class);

        if (paciente == null) {
            throw new RuntimeException("Paciente no encontrado");
        }

        return repository.save(cita);
    }

    public List<Cita> listar() {
        return repository.findAll();
    }
}