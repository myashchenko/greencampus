package ua.greencampus.service;

import ua.greencampus.entity.QuizQuestion;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizQuestionService {
    QuizQuestion create(QuizQuestion quizQuestion);
    QuizQuestion read(Long id);
    QuizQuestion update(QuizQuestion quizQuestion);
    void delete(QuizQuestion quizQuestion);
}
