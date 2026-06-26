package com.hospital_vm_cl.controller;

import com.hospital_vm_cl.model.Cita;
import org.springframework.web.bind.annotation.*;
import com.hospital_vm_cl.service.CitaService;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping
    public Cita crear(@RequestBody Cita cita) {
        return service.guardar(cita);
    }

    @GetMapping
    public List<Cita> listar() {
        return service.listar();
    }
}