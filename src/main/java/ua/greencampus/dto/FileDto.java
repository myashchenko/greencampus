package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Arsenii on 18.04.2016.
 */
@Getter
@Setter
public class FileDto {
    private Long id;
    private String path;
    private String name;

    public FileDto(Long id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    // hibernate need
    public FileDto() {
    }
}
