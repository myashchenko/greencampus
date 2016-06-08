package ua.greencampus.dto;

import ua.greencampus.entity.Quiz;
import ua.greencampus.entity.User;

import java.math.BigDecimal;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizResultDTO {
    private Long id;
    private UserDTO userDTO;
    private QuizDTO quizDTO;
    private BigDecimal score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public QuizDTO getQuizDTO() {
        return quizDTO;
    }

    public void setQuizDTO(QuizDTO quizDTO) {
        this.quizDTO = quizDTO;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
