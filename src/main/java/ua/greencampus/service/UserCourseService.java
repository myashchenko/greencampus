package ua.greencampus.service;

import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.entity.UserCourse;

import java.util.List;

/**
 * @author Ivan Mikho, created on 19.05.16.
 */
public interface UserCourseService {
    UserCourse create(UserCourse userCourse);
    UserCourse read(Long id);
    List<Course> readCreatedCourses(Long userId);
    List<Course> readParticipatingCourses(Long userId);
    UserCourse update(UserCourse userCourse);
    User getAuthorByCourse(Course course);
    void delete(UserCourse userCourse);
}
