package ua.greencampus.dto;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public class CourseThemeDto {
    private Long id;
    private String name;
    private List<String> files;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
