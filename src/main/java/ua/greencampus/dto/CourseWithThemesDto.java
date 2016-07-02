package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class CourseWithThemesDto extends CourseDto {
    private List<CourseThemeDto> courseThemes;
}
