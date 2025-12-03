package de.ait.training.config;

import de.ait.training.enums.Role;
import de.ait.training.model.User;
import de.ait.training.repository.SDUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class InitData {
    @Bean
    CommandLineRunner initUSers(SDUserRepository appUserRepository,
                                PasswordEncoder passwordEncoder) {
        return args -> {
            if(appUserRepository.count() == 0) {
                String userPassword = passwordEncoder.encode("user123");
                String adminPassword = passwordEncoder.encode("admin123");

                User SDUser = new User("user",userPassword, Role.EMPLOYEE);
                User adminUser = new User("admin",adminPassword, Role.ADMIN);

                appUserRepository.save(SDUser);
                appUserRepository.save(adminUser);
            }
        };
    }}
