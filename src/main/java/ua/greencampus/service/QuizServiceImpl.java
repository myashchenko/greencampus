package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.QuizDao;
import ua.greencampus.entity.Quiz;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;

    @Transactional
    @Override
    public Quiz create(Quiz quiz) {
        return quizDao.save(quiz);
    }

    @Transactional(readOnly = true)
    @Override
    public Quiz read(Long id) {
        return quizDao.findOne(id);
    }

    @Transactional
    @Override
    public Quiz update(Quiz quiz) {
        return quizDao.save(quiz);
    }

    @Transactional
    @Override
    public void delete(Quiz quiz) {
        quizDao.delete(quiz);
    }
}
