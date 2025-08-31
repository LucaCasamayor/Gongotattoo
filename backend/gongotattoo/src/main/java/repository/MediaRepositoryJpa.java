package repository;

import dtos.MediaDto;
import entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepositoryJpa extends JpaRepository<MediaEntity, Long> {
    List<MediaDto> findByType(String type);
}
