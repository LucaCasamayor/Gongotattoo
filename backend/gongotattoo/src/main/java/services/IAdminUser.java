package services;

import dtos.AdminUserDto;

public interface IAdminUser {
    AdminUserDto getAdminByUsername(String username);
    boolean validateCredentials(String username, String password);
}
