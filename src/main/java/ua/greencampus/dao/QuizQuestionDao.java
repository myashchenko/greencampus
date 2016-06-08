package ua.greencampus.dao;

import ua.greencampus.entity.QuizQuestion;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizQuestionDao {
    QuizQuestion create(QuizQuestion quizQuestion);
    QuizQuestion read(Long id);
    QuizQuestion update(QuizQuestion quizQuestion);
    void delete(QuizQuestion quizQuestion);
}
