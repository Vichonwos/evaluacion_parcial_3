package com.hospital_vm_cl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hospital_vm_cl.model.User;
import com.hospital_vm_cl.model.Role;
import com.hospital_vm_cl.repository.UserRepository;
@EnableScheduling
@SpringBootApplication
public class HospitalVmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalVmApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("admin").isEmpty()) {
                User user = new User();
                user.setUsername("admin");
                user.setPassword(encoder.encode("1234"));
                user.setRole(Role.ADMIN);
                repo.save(user);
            }
        };
    }
}