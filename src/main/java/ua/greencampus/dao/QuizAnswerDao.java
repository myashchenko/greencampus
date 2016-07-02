package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.QuizAnswer;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizAnswerDao extends JpaRepository<QuizAnswer, Long> {
}
