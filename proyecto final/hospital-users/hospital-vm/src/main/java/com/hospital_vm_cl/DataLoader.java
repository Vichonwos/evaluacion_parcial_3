package com.hospital_vm_cl;
import com.hospital_vm_cl.model.*;
import com.hospital_vm_cl.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import net.datafaker.Faker;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    private final TipoUsuarioRepository tipoUsuarioRepository;
    private final PacienteRepository pacienteRepository;
    private final FichaPacienteRepository fichaPacienteRepository;
    private final MedicoRepository medicoRepository;
    private final AtencionRepository atencionRepository;

    // Inyección por constructor
    public DataLoader(TipoUsuarioRepository tipoUsuarioRepository,
                      PacienteRepository pacienteRepository,
                      FichaPacienteRepository fichaPacienteRepository,
                      MedicoRepository medicoRepository,
                      AtencionRepository atencionRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.fichaPacienteRepository = fichaPacienteRepository;
        this.medicoRepository = medicoRepository;
        this.atencionRepository = atencionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        // 1. Poblado de Tipos de Usuario
        if (tipoUsuarioRepository.count() == 0) {
            String[] tipos = {"Fonasa", "Isapre", "Particular"};
            for (String t : tipos) {
                TipoUsuario tipo = new TipoUsuario();
                tipo.setNombre(t);
                tipoUsuarioRepository.save(tipo);
            }
        }
        List<TipoUsuario> listaTipos = tipoUsuarioRepository.findAll();

        // 2. Poblado de Médicos
        if (medicoRepository.count() == 0) {
            for (int i = 0; i < 5; i++) {
                Medico medico = new Medico();
                medico.setRunMedico(faker.idNumber().valid().substring(0, 9));
                medico.setNombreCompleto(faker.name().fullName());
                medico.setEspecialidad(faker.medical().hospitalDepartment());
                medico.setJefeTurno(faker.options().option("S", "N").charAt(0));
                medicoRepository.save(medico);
            }
        }
        List<Medico> listaMedicos = medicoRepository.findAll();

        // 3. Poblado de Pacientes y Fichas Médicas (Relación 1 a 1)
        if (pacienteRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Paciente paciente = new Paciente();
                paciente.setRun(faker.idNumber().valid().substring(0, 9));
                paciente.setNombres(faker.name().firstName());
                paciente.setApellidos(faker.name().lastName());
                paciente.setFechaNacimiento(faker.date().birthday());
                paciente.setCorreo(faker.internet().emailAddress());
                paciente.setTipoUsuario(listaTipos.get(random.nextInt(listaTipos.size())));
                Paciente pacGuardado = pacienteRepository.save(paciente);

                // Crear Ficha Médica obligatoria vinculada
                FichaPaciente ficha = new FichaPaciente();
                ficha.setPaciente(pacGuardado);
                ficha.setDatosPersonales(faker.lorem().sentence());
                ficha.setDatosPersonales2(faker.lorem().sentence());
                ficha.setDatosPersonales3(faker.lorem().sentence());
                ficha.setDatosPersonales4(faker.lorem().sentence());
                ficha.setDatosPersonales5(faker.lorem().sentence());
                fichaPacienteRepository.save(ficha);
            }
        }
        List<Paciente> listaPacientes = pacienteRepository.findAll();

        // 4. Poblado de Atenciones Médicas
        if (atencionRepository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                Atencion atencion = new Atencion();
                atencion.setFechaAtencion(new Date());
                atencion.setHoraAtencion(new Date());
                atencion.setCosto((double) faker.number().numberBetween(15000, 90000));
                atencion.setPaciente(listaPacientes.get(random.nextInt(listaPacientes.size())));
                atencion.setMedico(listaMedicos.get(random.nextInt(listaMedicos.size())));
                atencion.setComentarios(faker.lorem().sentence());
                atencionRepository.save(atencion);
            }
        }

        System.out.println("✅ [DataFaker] Base de datos del Hospital V&M poblada con éxito.");
    }
}

