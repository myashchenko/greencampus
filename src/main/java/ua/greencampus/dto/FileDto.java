package ua.greencampus.dto;

/**
 * Created by Arsenii on 18.04.2016.
 */
public class FileDto {
    private Long id;
    private String path;
    private String name;

    public FileDto(Long id, String path, String name) {
        this.id = id;
        this.path = path;
        this.name = name;
    }

    public FileDto(String path) {
        this.path = path;
    }

    public FileDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
