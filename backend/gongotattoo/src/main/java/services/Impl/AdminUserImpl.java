package services.Impl;

import dtos.AdminUserDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import models.AdminUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.AdminUserRepositoryJpa;
import security.JwtUtil;
import services.IAdminUser;
@Service
@RequiredArgsConstructor
public class AdminUserImpl implements IAdminUser {

    private final AdminUserRepositoryJpa adminUserRepositoryJpa;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    @Getter
    private final JwtUtil jwtUtil; // inyectamos JwtUtil

    @Override
    public AdminUserDto getAdminByUsername(String username) {
        AdminUser admin = adminUserRepositoryJpa.findByUsername(username)
                .orElse(null);
        return admin != null ? modelMapper.map(admin, AdminUserDto.class) : null;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        AdminUser admin = adminUserRepositoryJpa.findByUsername(username)
                .orElse(null);
        return admin != null && passwordEncoder.matches(password, admin.getPassword());
    }

    public String loginAndGetToken(String username, String password) {
        if (validateCredentials(username, password)) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Usuario o contraseña incorrectos");
    }


}


