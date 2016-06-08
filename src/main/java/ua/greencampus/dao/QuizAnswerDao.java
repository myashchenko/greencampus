package ua.greencampus.dao;

import ua.greencampus.entity.QuizAnswer;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizAnswerDao {
    QuizAnswer create(QuizAnswer quizAnswer);
    QuizAnswer read(Long id);
    QuizAnswer update(QuizAnswer quizAnswer);
    void delete(QuizAnswer quizAnswer);
}
