package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class CourseThemeDto {
    private Long id;
    private String name;
    private List<String> files;
}
