package ua.greencampus.service;

import ua.greencampus.entity.QuizResult;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizResultService {
    QuizResult create(QuizResult quizResult);
    QuizResult read(Long id);
    QuizResult update(QuizResult quizResult);
    void delete(QuizResult quizResult);
}
