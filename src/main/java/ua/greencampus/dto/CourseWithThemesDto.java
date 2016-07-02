package ua.greencampus.dto;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public class CourseWithThemesDto extends CourseDto {
    private List<CourseThemeDto> courseThemes;

    public List<CourseThemeDto> getCourseThemes() {
        return courseThemes;
    }

    public void setCourseThemes(List<CourseThemeDto> courseThemes) {
        this.courseThemes = courseThemes;
    }
}
