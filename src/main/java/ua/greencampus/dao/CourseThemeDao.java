package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.CourseTheme;

/**
 * @author Nikolay Yashchenko
 */
public interface CourseThemeDao extends JpaRepository<CourseTheme, Long> {
}
