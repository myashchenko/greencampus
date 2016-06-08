package ua.greencampus.dao;

import org.springframework.stereotype.Repository;
import ua.greencampus.entity.QuizResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class QuizResultDaoImpl implements QuizResultDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public QuizResult create(QuizResult quizResult) {
        entityManager.persist(quizResult);
        return quizResult;
    }

    @Override
    public QuizResult read(Long id) {
        return entityManager.find(QuizResult.class, id);
    }

    @Override
    public QuizResult update(QuizResult quizResult) {
        return entityManager.merge(quizResult);
    }

    @Override
    public void delete(QuizResult quizResult) {
        entityManager.remove(quizResult);
    }
}
