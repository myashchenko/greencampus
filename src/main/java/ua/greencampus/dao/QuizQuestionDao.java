package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.QuizQuestion;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizQuestionDao extends JpaRepository<QuizQuestion, Long> {
}
