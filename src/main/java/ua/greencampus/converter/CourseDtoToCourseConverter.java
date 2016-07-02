package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDto;
import ua.greencampus.dto.CourseWithThemesDto;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.service.CourseService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 15.04.16.
 */
public class CourseDtoToCourseConverter implements Converter<CourseWithThemesDto, Course> {

    private CourseService courseService;
    private ConversionService conversionService;

    public CourseDtoToCourseConverter(CourseService courseService, ConversionService conversionService) {
        this.courseService = courseService;
        this.conversionService = conversionService;
    }

    @Override
    public Course convert(CourseWithThemesDto courseDto) {

        Course course = null;
        Long courseId = courseDto.getId();

        if (courseId != null) {
            course = courseService.read(courseId);
        }

        if (course == null) {
            course = new Course();
        }

        course.setTitle(courseDto.getTitle());
        course.setDescription(courseDto.getDescription());

        if (course.getThemes() != null) {
            List<CourseTheme> courseThemeList = new ArrayList<>();
            for (CourseThemeDto courseThemeDTO : courseDto.getCourseThemes()) {
                courseThemeList.add(conversionService.convert(courseThemeDTO, CourseTheme.class));
            }
            course.setThemes(courseThemeList);
        }

        return course;
    }
}
