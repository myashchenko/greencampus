package ua.greencampus.dao;

import ua.greencampus.entity.QuizResult;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizResultDao {
    QuizResult create(QuizResult quizResult);
    QuizResult read(Long id);
    QuizResult update(QuizResult quizResult);
    void delete(QuizResult quizResult);
}
