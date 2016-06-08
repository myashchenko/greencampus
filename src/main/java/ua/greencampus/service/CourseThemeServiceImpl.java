package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.CourseThemeDao;
import ua.greencampus.entity.CourseTheme;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class CourseThemeServiceImpl implements CourseThemeService {

    @Autowired
    private CourseThemeDao courseThemeDao;

    @Transactional
    @Override
    public CourseTheme create(CourseTheme courseTheme) {
        return courseThemeDao.create(courseTheme);
    }

    @Transactional(readOnly = true)
    @Override
    public CourseTheme read(Long id) {
        return courseThemeDao.read(id);
    }

    @Transactional
    @Override
    public CourseTheme update(CourseTheme courseTheme) {
        return courseThemeDao.update(courseTheme);
    }

    @Transactional
    @Override
    public void delete(CourseTheme courseTheme) {
        courseThemeDao.delete(courseTheme);
    }
}
