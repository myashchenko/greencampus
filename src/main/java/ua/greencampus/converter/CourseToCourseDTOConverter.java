package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseDTO;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserCourseService;

import java.util.Optional;

/**
 * @author Nikolay Yashchenko
 */
public class CourseToCourseDTOConverter implements Converter<Course, CourseDTO> {

    private UserCourseService userCourseService;

    public CourseToCourseDTOConverter(UserCourseService userCourseService) {
        this.userCourseService = userCourseService;
    }

    @Override
    public CourseDTO convert(Course course) {
        if (course == null) return null;

        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setAuthor(Optional.ofNullable(userCourseService.getAuthorByCourse(course)).map(User::getName).orElse(null));

        return courseDTO;
    }
}
