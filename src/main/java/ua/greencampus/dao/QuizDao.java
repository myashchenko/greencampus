package ua.greencampus.dao;

import ua.greencampus.entity.Quiz;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizDao {
    Quiz create(Quiz quiz);
    Quiz read(Long id);
    Quiz update(Quiz quiz);
    void delete(Quiz quiz);
}
