package com.gongotattoo.backend.services.Impl;

import com.gongotattoo.backend.dtos.MediaDto;
import com.gongotattoo.backend.entities.MediaEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.gongotattoo.backend.repository.MediaRepositoryJpa;
import com.gongotattoo.backend.services.IMediaService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements IMediaService {

    private final MediaRepositoryJpa mediaRepositoryJpa;
    private final ModelMapper modelMapper;

    @Override
    public List<MediaDto> getAllMedia() {
        return mediaRepositoryJpa.findAll().stream()
                .map(p -> modelMapper.map(p, MediaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MediaDto> getMediaByType(String type) {
        return mediaRepositoryJpa.findByType(type).stream()
                .map(p -> modelMapper.map(p, MediaDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public MediaDto saveMedia(MediaDto mediaDto) {
        MediaEntity entity = modelMapper.map(mediaDto, MediaEntity.class);
        MediaEntity saved = mediaRepositoryJpa.save(entity);

        return modelMapper.map(saved, MediaDto.class);
    }


    @Override
    public void deleteMedia(Long id) {
        if (mediaRepositoryJpa.existsById(id)) {
            mediaRepositoryJpa.deleteById(id);
        } else{
            throw new EntityNotFoundException("Photo with id: " + id + " not found");
        }
    }
}
