package ua.greencampus.service;

import ua.greencampus.entity.Quiz;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizService {
    Quiz create(Quiz quiz);
    Quiz read(Long id);
    Quiz update(Quiz quiz);
    void delete(Quiz quiz);
}
