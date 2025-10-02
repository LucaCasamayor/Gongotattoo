package com.gongotattoo.backend.config;

import com.gongotattoo.backend.entities.AdminUserEntity;
import com.gongotattoo.backend.repository.AdminUserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {

    private final AdminUserRepositoryJpa repo;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) {
        if (repo.findByUsername("admin").isEmpty()) {
            AdminUserEntity admin = new AdminUserEntity();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            repo.save(admin);
            System.out.println("⚡ Usuario admin creado: admin/admin123");
        }
    }
}
