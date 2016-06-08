package ua.greencampus.dao;

import org.springframework.stereotype.Repository;
import ua.greencampus.entity.Quiz;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class QuizDaoImpl implements QuizDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Quiz create(Quiz quiz) {
        entityManager.persist(quiz);
        return quiz;
    }

    @Override
    public Quiz read(Long id) {
        return entityManager.find(Quiz.class, id);
    }

    @Override
    public Quiz update(Quiz quiz) {
        return entityManager.merge(quiz);
    }

    @Override
    public void delete(Quiz quiz) {
        entityManager.remove(quiz);
    }
}
