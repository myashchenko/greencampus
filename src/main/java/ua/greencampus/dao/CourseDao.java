package ua.greencampus.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import ua.greencampus.entity.Course;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface CourseDao extends PagingAndSortingRepository<Course, Long> {

    @Query(value = "SELECT * FROM course WHERE to_tsvector(title || ' ' || description) @@ to_tsquery(:keywords)",
            nativeQuery = true)
    List<Course> findByKeywords(@Param("keywords") String keywords);
}
