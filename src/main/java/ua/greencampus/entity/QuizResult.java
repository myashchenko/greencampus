package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz_result")
@Getter
@Setter
public class QuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Quiz quiz;

    @Column(name = "score", nullable = false)
    private BigDecimal score;

}
