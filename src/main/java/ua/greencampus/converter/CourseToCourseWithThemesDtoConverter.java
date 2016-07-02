package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDto;
import ua.greencampus.dto.CourseWithThemesDto;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserCourseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
public class CourseToCourseWithThemesDtoConverter implements Converter<Course, CourseWithThemesDto> {

    private UserCourseService userCourseService;
    private ConversionService conversionService;

    public CourseToCourseWithThemesDtoConverter(UserCourseService userCourseService,
                                                ConversionService conversionService) {
        this.userCourseService = userCourseService;
        this.conversionService = conversionService;
    }


    @Override
    public CourseWithThemesDto convert(Course course) {
        if (course == null) return null;

        CourseWithThemesDto courseDto = new CourseWithThemesDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setDescription(course.getDescription());
        courseDto.setAuthor(
                Optional.ofNullable(userCourseService.getAuthorByCourse(course))
                        .map(User::getName)
                        .orElse(null)
        );

        List<CourseThemeDto> courseThemeDtoList = course.getThemes().stream()
                .map(t -> conversionService.convert(t, CourseThemeDto.class))
                .collect(Collectors.toList());
        courseDto.setCourseThemes(courseThemeDtoList);

        return courseDto;
    }
}
