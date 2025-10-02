package com.gongotattoo.backend.repository;

import com.gongotattoo.backend.dtos.MediaDto;
import com.gongotattoo.backend.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepositoryJpa extends JpaRepository<MediaEntity, Long> {
    List<MediaDto> findByType(String type);
}
