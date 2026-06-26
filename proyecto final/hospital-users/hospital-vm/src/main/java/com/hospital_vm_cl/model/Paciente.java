package com.hospital_vm_cl.model;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
@Entity @Table(name = "Paciente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; @Column(unique = true, length = 13, nullable = false)
    private String run; @Column(nullable = false)
    private String nombre; @Column(nullable = false)
    private String apellido; @Column(nullable = false)
    private String correo; @Column(nullable = true) private Date fechaNacimiento; }