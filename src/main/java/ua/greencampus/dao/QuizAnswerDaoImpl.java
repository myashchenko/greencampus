package ua.greencampus.dao;

import org.springframework.stereotype.Repository;
import ua.greencampus.entity.QuizAnswer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class QuizAnswerDaoImpl implements QuizAnswerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public QuizAnswer create(QuizAnswer quizAnswer) {
        entityManager.persist(quizAnswer);
        return quizAnswer;
    }

    @Override
    public QuizAnswer read(Long id) {
        return entityManager.find(QuizAnswer.class, id);
    }

    @Override
    public QuizAnswer update(QuizAnswer quizAnswer) {
        return entityManager.merge(quizAnswer);
    }

    @Override
    public void delete(QuizAnswer quizAnswer) {
        entityManager.remove(quizAnswer);
    }
}
