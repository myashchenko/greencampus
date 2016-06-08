package ua.greencampus.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.UserCourseDao;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.User;
import ua.greencampus.entity.UserCourse;

import java.util.List;

/**
 * @author Ivan Mikho, created on 19.05.16.
 */

@Service
public class UserCourseServiceImpl implements UserCourseService {

    @Autowired
    private UserCourseDao userCourseDao;

    @Transactional
    @Override
    public UserCourse create(UserCourse userCourse) {
        return userCourseDao.create(userCourse);
    }

    @Transactional(readOnly = true)
    @Override
    public UserCourse read(Long id) {
        return userCourseDao.read(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> readCreatedCourses(Long userId) {
        return userCourseDao.readCreatedCourses(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> readParticipatingCourses(Long userId) {
        return userCourseDao.readParticipatingCourses(userId);
    }

    @Transactional
    @Override
    public void delete(UserCourse userCourse) {
        userCourseDao.delete(userCourse);
    }

    @Transactional
    @Override
    public UserCourse update(UserCourse userCourse) {
        return userCourseDao.update(userCourse);
    }

    @Transactional
    @Override
    public User getAuthorByCourse(Course course) {
        User authorByCourse = userCourseDao.getAuthorByCourse(course);
        Hibernate.initialize(authorByCourse);
        return authorByCourse;
    }
}
