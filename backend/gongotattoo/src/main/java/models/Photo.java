package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    private Long id;
    private String name;
    private String url;
    private LocalDateTime uploadedAt;
    private String type;
}
