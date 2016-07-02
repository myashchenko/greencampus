package ua.greencampus.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import ua.greencampus.entity.Course;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface CourseDao extends PagingAndSortingRepository<Course, Long> {
}
