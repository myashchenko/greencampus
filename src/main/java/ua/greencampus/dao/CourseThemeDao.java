package ua.greencampus.dao;

import ua.greencampus.entity.CourseTheme;

/**
 * @author Nikolay Yashchenko
 */
public interface CourseThemeDao {
    CourseTheme create(CourseTheme courseTheme);
    CourseTheme read(Long id);
    CourseTheme update(CourseTheme courseTheme);
    void delete(CourseTheme courseTheme);
}
