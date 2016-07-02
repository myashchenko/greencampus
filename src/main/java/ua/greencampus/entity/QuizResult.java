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
    @SequenceGenerator(name = "quiz_result_seq_gen", sequenceName = "quiz_result_seq", allocationSize = 1)
    @GeneratedValue(generator = "quiz_result_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Quiz quiz;

    @Column(name = "score", nullable = false)
    private BigDecimal score;

}
