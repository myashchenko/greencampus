package ua.greencampus.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ua.greencampus.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Course create(Course lecture) {
        entityManager.persist(lecture);
        return lecture;
    }

    @Override
    public Course read(Long id) {
        return entityManager.find(Course.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> getByParams(int offset, int size, String sort) {
        return ((Session) entityManager.getDelegate()).createCriteria(Course.class)
                .setFirstResult(offset * size)
                .setMaxResults(size)
                .list();
    }

    @Override
    public Course update(Course lecture) {
        return entityManager.merge(lecture);
    }

    @Override
    public void delete(Course lecture) {
        entityManager.remove(lecture);
    }
}
