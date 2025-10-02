package com.gongotattoo.backend.controllers;

import com.gongotattoo.backend.dtos.MediaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gongotattoo.backend.services.IMediaService;

import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class MediaController {

    private final IMediaService mediaService;

    @GetMapping
    public ResponseEntity<List<MediaDto>> getAll() {
        List<MediaDto> photos = mediaService.getAllMedia();
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<MediaDto>> getAllByType(@PathVariable String type) {
        List<MediaDto> photos = mediaService.getMediaByType(type);
        return ResponseEntity.ok(photos);
    }

    @PostMapping
    public ResponseEntity<MediaDto> savePhoto(@RequestBody MediaDto mediaDto) {
        MediaDto saved = mediaService.saveMedia(mediaDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
