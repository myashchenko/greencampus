package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.QuizAnswerDao;
import ua.greencampus.entity.QuizAnswer;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class QuizAnswerServiceImpl implements QuizAnswerService {

    @Autowired
    private QuizAnswerDao quizAnswerDao;

    @Transactional
    @Override
    public QuizAnswer create(QuizAnswer quizAnswer) {
        return quizAnswerDao.save(quizAnswer);
    }

    @Transactional(readOnly = true)
    @Override
    public QuizAnswer read(Long id) {
        return quizAnswerDao.findOne(id);
    }

    @Transactional
    @Override
    public QuizAnswer update(QuizAnswer quizAnswer) {
        return quizAnswerDao.save(quizAnswer);
    }

    @Transactional
    @Override
    public void delete(QuizAnswer quizAnswer) {
        quizAnswerDao.delete(quizAnswer);
    }
}
