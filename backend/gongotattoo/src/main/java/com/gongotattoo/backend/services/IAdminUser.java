package com.gongotattoo.backend.services;

import com.gongotattoo.backend.dtos.AdminUserDto;

public interface IAdminUser {
    AdminUserDto getAdminByUsername(String username);
    boolean validateCredentials(String username, String password);
}
