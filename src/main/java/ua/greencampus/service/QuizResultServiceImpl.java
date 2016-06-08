package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.QuizResultDao;
import ua.greencampus.entity.QuizResult;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class QuizResultServiceImpl implements QuizResultService {

    @Autowired
    private QuizResultDao quizResultDao;

    @Transactional
    @Override
    public QuizResult create(QuizResult quizResult) {
        return quizResultDao.create(quizResult);
    }

    @Transactional(readOnly = true)
    @Override
    public QuizResult read(Long id) {
        return quizResultDao.read(id);
    }

    @Transactional
    @Override
    public QuizResult update(QuizResult quizResult) {
        return quizResultDao.update(quizResult);
    }

    @Transactional
    @Override
    public void delete(QuizResult quizResult) {
        quizResultDao.delete(quizResult);
    }
}
