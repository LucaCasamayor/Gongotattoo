package com.gongotattoo.backend.services;

import com.gongotattoo.backend.dtos.MediaDto;

import java.util.List;

public interface IMediaService {
    List<MediaDto> getAllMedia();
    List<MediaDto> getMediaByType(String type);
    MediaDto saveMedia(MediaDto media);
    void deleteMedia(Long id);
}
