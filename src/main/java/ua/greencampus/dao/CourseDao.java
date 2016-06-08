package ua.greencampus.dao;

import ua.greencampus.entity.Course;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface CourseDao {
    Course create(Course lecture);
    Course read(Long id);
    List<Course> getByParams(int offset, int size, String sort);
    Course update(Course lecture);
    void delete(Course lecture);
}
