package ua.greencampus.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.CourseDao;
import ua.greencampus.entity.Course;
import ua.greencampus.entity.UserCourse;
import ua.greencampus.entity.UserCourseRole;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao lectureDao;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCourseService userCourseService;

    @Transactional
    @Override
    public Course create(Course lecture) {
        lecture = lectureDao.save(lecture);
        userCourseService.create(new UserCourse(
                userService.read(authenticationService.getLoggedInUserId()), lecture, UserCourseRole.CREATOR)
        );
        return lecture;
    }

    @Transactional(readOnly = true)
    @Override
    public Course read(Long id) {
        return lectureDao.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Course readWithThemes(Long id) {
        Course course = lectureDao.findOne(id);
        Hibernate.initialize(course.getThemes());
        return course;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> getByParams(int page, int size, String sort) {
        // todo sorting
        return lectureDao.findAll(new PageRequest(page, size)).getContent();
    }

    @Transactional
    @Override
    public Course update(Course lecture) {
        return lectureDao.save(lecture);
    }

    @Transactional
    @Override
    public void delete(Course lecture) {
        lectureDao.delete(lecture);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Course> search(String keywords) {
        return lectureDao.findByKeywords(keywords);
    }
}
