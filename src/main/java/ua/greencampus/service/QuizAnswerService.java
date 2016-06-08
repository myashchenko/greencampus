package ua.greencampus.service;

import ua.greencampus.entity.QuizAnswer;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizAnswerService {
    QuizAnswer create(QuizAnswer quizAnswer);
    QuizAnswer read(Long id);
    QuizAnswer update(QuizAnswer quizAnswer);
    void delete(QuizAnswer quizAnswer);
}
