package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseDTO;
import ua.greencampus.dto.CourseThemeDTO;
import ua.greencampus.dto.CourseWithThemesDTO;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.service.CourseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 15.04.16.
 */
public class CourseDTOToCourseConverter implements Converter<CourseWithThemesDTO, Course> {

    private CourseService courseService;
    private ConversionService conversionService;

    public CourseDTOToCourseConverter(CourseService courseService, ConversionService conversionService) {
        this.courseService = courseService;
        this.conversionService = conversionService;
    }

    @Override
    public Course convert(CourseWithThemesDTO courseDTO) {

        Course course = null;
        Long courseId = courseDTO.getId();

        if (courseId != null) {
            course = courseService.read(courseId);
        }

        if (course == null) {
            course = new Course();
        }

        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());

        if (course.getThemes() != null) {
            List<CourseTheme> courseThemeList = new ArrayList<>();
            for (CourseThemeDTO courseThemeDTO : courseDTO.getCourseThemeDTOList()) {
                courseThemeList.add(conversionService.convert(courseThemeDTO, CourseTheme.class));
            }
            course.setThemes(courseThemeList);
        }

        return course;
    }
}
