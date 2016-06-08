package ua.greencampus.dao;

import org.springframework.stereotype.Repository;
import ua.greencampus.entity.CourseTheme;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class CourseThemeDaoImpl implements CourseThemeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CourseTheme create(CourseTheme courseTheme) {
        entityManager.persist(courseTheme);
        return courseTheme;
    }

    @Override
    public CourseTheme read(Long id) {
        return entityManager.find(CourseTheme.class, id);
    }

    @Override
    public CourseTheme update(CourseTheme courseTheme) {
        return entityManager.merge(courseTheme);
    }

    @Override
    public void delete(CourseTheme courseTheme) {
        entityManager.remove(courseTheme);
    }
}
