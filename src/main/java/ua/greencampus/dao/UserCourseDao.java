package ua.greencampus.dao;

import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.entity.UserCourse;

import java.util.List;

/**
 * @author Ivan Mikho, created on 18.05.16.
 */
public interface UserCourseDao {
    UserCourse create(UserCourse user);
    UserCourse read(Long id);
    List<Course> readCreatedCourses(Long userId);
    List<Course> readParticipatingCourses(Long userId);
    UserCourse update(UserCourse user);
    User getAuthorByCourse(Course course);
    void delete(UserCourse user);
}
