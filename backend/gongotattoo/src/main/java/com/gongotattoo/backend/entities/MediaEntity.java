package com.gongotattoo.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity @Table(name="media")
@Data @AllArgsConstructor @NoArgsConstructor
public class MediaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private java.time.LocalDateTime uploadedAt;

    @Column(name = "media_type")
    private String type;
}

