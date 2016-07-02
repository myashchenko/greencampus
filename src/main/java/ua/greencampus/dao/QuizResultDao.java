package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.QuizResult;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizResultDao extends JpaRepository<QuizResult, Long> {
}
