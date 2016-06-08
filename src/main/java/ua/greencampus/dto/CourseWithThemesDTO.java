package ua.greencampus.dto;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public class CourseWithThemesDTO extends CourseDTO {
    private List<CourseThemeDTO> courseThemeDTOList;

    public List<CourseThemeDTO> getCourseThemeDTOList() {
        return courseThemeDTOList;
    }

    public void setCourseThemeDTOList(List<CourseThemeDTO> courseThemeDTOList) {
        this.courseThemeDTOList = courseThemeDTOList;
    }
}
