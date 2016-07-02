package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.Quiz;

/**
 * @author Nikolay Yashchenko
 */
public interface QuizDao extends JpaRepository<Quiz, Long> {
}
