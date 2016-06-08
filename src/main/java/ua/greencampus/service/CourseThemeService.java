package ua.greencampus.service;

import ua.greencampus.entity.CourseTheme;

/**
 * @author Nikolay Yashchenko
 */
public interface CourseThemeService {
    CourseTheme create(CourseTheme courseTheme);
    CourseTheme read(Long id);
    CourseTheme update(CourseTheme courseTheme);
    void delete(CourseTheme courseTheme);
}
