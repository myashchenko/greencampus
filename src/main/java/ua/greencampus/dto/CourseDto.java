package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private String author;
}
