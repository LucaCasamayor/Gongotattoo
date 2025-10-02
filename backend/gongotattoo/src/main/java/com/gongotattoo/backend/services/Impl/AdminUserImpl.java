package com.gongotattoo.backend.services.Impl;

import com.gongotattoo.backend.dtos.AdminUserDto;
import com.gongotattoo.backend.entities.AdminUserEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.gongotattoo.backend.repository.AdminUserRepositoryJpa;
import com.gongotattoo.backend.security.JwtUtil;
import com.gongotattoo.backend.services.IAdminUser;
@Service
@RequiredArgsConstructor
public class AdminUserImpl implements IAdminUser {

    private final AdminUserRepositoryJpa adminUserRepositoryJpa;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Getter
    private final JwtUtil jwtUtil;

    @Override
    public AdminUserDto getAdminByUsername(String username) {
        AdminUserEntity admin = adminUserRepositoryJpa.findByUsername(username).orElse(null);
        return admin != null ? modelMapper.map(admin, AdminUserDto.class) : null;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        AdminUserEntity admin = adminUserRepositoryJpa.findByUsername(username).orElse(null);
        return admin != null && passwordEncoder.matches(password, admin.getPassword());
    }

    public String loginAndGetToken(String username, String password) {
        if (validateCredentials(username, password)) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Usuario o contraseña incorrectos");
    }
}



