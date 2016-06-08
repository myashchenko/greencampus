package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDTO;
import ua.greencampus.dto.CourseWithThemesDTO;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserCourseService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
public class CourseToCourseWithThemesDTOConverter implements Converter<Course, CourseWithThemesDTO> {

    private UserCourseService userCourseService;
    private ConversionService conversionService;

    public CourseToCourseWithThemesDTOConverter(UserCourseService userCourseService,
                                                ConversionService conversionService) {
        this.userCourseService = userCourseService;
        this.conversionService = conversionService;
    }


    @Override
    public CourseWithThemesDTO convert(Course course) {
        if (course == null) return null;

        CourseWithThemesDTO courseDTO = new CourseWithThemesDTO();
        courseDTO.setId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setAuthor(
                Optional.ofNullable(userCourseService.getAuthorByCourse(course))
                        .map(User::getName)
                        .orElse(null)
        );

        List<CourseThemeDTO> courseThemeDTOList = course.getThemes().stream()
                .map(t -> conversionService.convert(t, CourseThemeDTO.class))
                .collect(Collectors.toList());
        courseDTO.setCourseThemeDTOList(courseThemeDTOList);

        return courseDTO;
    }
}
