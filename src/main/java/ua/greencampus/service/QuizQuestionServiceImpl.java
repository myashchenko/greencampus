package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.QuizQuestionDao;
import ua.greencampus.entity.QuizQuestion;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class QuizQuestionServiceImpl implements QuizQuestionService {

    @Autowired
    private QuizQuestionDao quizQuestionDao;

    @Transactional
    @Override
    public QuizQuestion create(QuizQuestion quizQuestion) {
        return quizQuestionDao.create(quizQuestion);
    }

    @Transactional(readOnly = true)
    @Override
    public QuizQuestion read(Long id) {
        return quizQuestionDao.read(id);
    }

    @Transactional
    @Override
    public QuizQuestion update(QuizQuestion quizQuestion) {
        return quizQuestionDao.update(quizQuestion);
    }

    @Transactional
    @Override
    public void delete(QuizQuestion quizQuestion) {
        quizQuestionDao.delete(quizQuestion);
    }
}
