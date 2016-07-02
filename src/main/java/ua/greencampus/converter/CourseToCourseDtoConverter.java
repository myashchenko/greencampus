package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseDto;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserCourseService;

import java.util.Optional;

/**
 * @author Nikolay Yashchenko
 */
public class CourseToCourseDtoConverter implements Converter<Course, CourseDto> {

    private UserCourseService userCourseService;

    public CourseToCourseDtoConverter(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @Override
    public CourseDto convert(Course course) {
        if (course == null) return null;

        CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setTitle(course.getTitle());
        courseDto.setDescription(course.getDescription());
        courseDto.setAuthor(Optional.ofNullable(userCourseService.getAuthorByCourse(course))
                .map(User::getName).orElse(null));

        return courseDto;
    }
}
