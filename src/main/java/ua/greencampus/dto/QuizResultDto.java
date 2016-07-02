package ua.greencampus.dto;

import java.math.BigDecimal;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizResultDto {
    private Long id;
    private UserDto user;
    private QuizDto quiz;
    private BigDecimal score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public QuizDto getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizDto quiz) {
        this.quiz = quiz;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
