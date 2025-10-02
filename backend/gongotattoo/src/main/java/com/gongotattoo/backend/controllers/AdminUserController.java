package com.gongotattoo.backend.controllers;

import com.gongotattoo.backend.dtos.AdminUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gongotattoo.backend.services.Impl.AdminUserImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AdminUserController {

    private final AdminUserImpl adminUserService;

    // Endpoint protegido con JWT
    @GetMapping("/{username}")
    public ResponseEntity<AdminUserDto> getAdmin(
            @PathVariable String username,
            @RequestHeader("Authorization") String authHeader) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7); // quitar "Bearer "

        // Validar token
        if (!adminUserService.getJwtUtil().validateToken(token, username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        AdminUserDto admin = adminUserService.getAdminByUsername(username);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }

    // Login que devuelve token JWT
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody AdminUserDto adminUserDto) {
        try {
            String token = adminUserService.loginAndGetToken(
                    adminUserDto.getUsername(),
                    adminUserDto.getPassword()
            );

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}

