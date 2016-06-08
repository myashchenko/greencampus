package ua.greencampus.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz_result")
public class QuizResult {

    @Id
    @SequenceGenerator(name = "quiz_result_seq_gen", sequenceName = "quiz_result_seq", allocationSize = 1)
    @GeneratedValue(generator = "quiz_result_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Quiz quiz;

    @Column(name = "score", nullable = false)
    private BigDecimal score;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }
}
