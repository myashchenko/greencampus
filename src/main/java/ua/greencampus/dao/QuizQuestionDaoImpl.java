package ua.greencampus.dao;

import org.springframework.stereotype.Repository;
import ua.greencampus.entity.QuizQuestion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class QuizQuestionDaoImpl implements QuizQuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public QuizQuestion create(QuizQuestion quizQuestion) {
        entityManager.persist(quizQuestion);
        return quizQuestion;
    }

    @Override
    public QuizQuestion read(Long id) {
        return entityManager.find(QuizQuestion.class, id);
    }

    @Override
    public QuizQuestion update(QuizQuestion quizQuestion) {
        return entityManager.merge(quizQuestion);
    }

    @Override
    public void delete(QuizQuestion quizQuestion) {
        entityManager.remove(quizQuestion);
    }
}
