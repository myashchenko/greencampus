package ua.greencampus.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.entity.UserCourse;
import ua.greencampus.entity.UserCourseRole;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Ivan Mikho, created on 18.05.16.
 */

@Repository
public class UserCourseDaoImpl implements UserCourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserCourse create(UserCourse userCourse) {
        entityManager.persist(userCourse);
        return userCourse;
    }

    @Override
    public UserCourse read(Long id) {
        return entityManager.find(UserCourse.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> readParticipatingCourses(Long userId) {
        return ((Session) entityManager.getDelegate()).createCriteria(UserCourse.class)
                .createAlias("user","u")
                .add(Restrictions.eq("u.id", userId))
                .add(Restrictions.eq("userRole", UserCourseRole.PARTICIPANT))
                .setProjection(Projections.property("course"))
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Course> readCreatedCourses(Long userId) {
        return ((Session) entityManager.getDelegate()).createCriteria(UserCourse.class)
                .createAlias("user","u")
                .add(Restrictions.eq("u.id", userId))
                .add(Restrictions.eq("userRole", UserCourseRole.CREATOR))
                .setProjection(Projections.property("course"))
                .list();
    }

    @Override
    public UserCourse update(UserCourse userCourse) {
        return entityManager.merge(userCourse);
    }

    @Override
    public void delete(UserCourse userCourse) {
        entityManager.remove(userCourse);
    }

    @Override
    public User getAuthorByCourse(Course course) {
        return (User) entityManager.unwrap(Session.class).createCriteria(UserCourse.class)
                .add(
                        Restrictions.and(
                                Restrictions.eq("course", course),
                                Restrictions.eq("userRole", UserCourseRole.CREATOR)
                        )
                )
                .setProjection(Projections.property("user"))
                .uniqueResult();
    }
}
