package ua.greencampus.service;

import ua.greencampus.entity.Course;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface CourseService {
    Course create(Course lecture);
    Course read(Long id);
    Course readWithThemes(Long id);
    List<Course> getByParams(int offset, int size, String sort);
    Course update(Course lecture);
    void delete(Course lecture);
}
