package com.hospital_vm_cl.controller;

import com.hospital_vm_cl.dto.PacienteDTO;
import com.hospital_vm_cl.service.CargaMasivaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class CargaMasivaController {

    @Autowired
    private CargaMasivaService service;

    @PostMapping("/masivo")
    public ResponseEntity<?> cargar(@RequestBody List<PacienteDTO> lista) {

        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.badRequest().body("Lista vacía");
        }

        service.guardarMasivo(lista);

        return ResponseEntity.ok("Carga masiva exitosa: " + lista.size());
    }

}
