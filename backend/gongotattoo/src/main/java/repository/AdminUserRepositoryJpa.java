package repository;

import dtos.AdminUserDto;
import entities.AdminUserEntity;
import models.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepositoryJpa extends JpaRepository<AdminUserEntity, Long> {
    Optional<AdminUser> findByUsername(String username);
}
