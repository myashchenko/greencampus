package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz_answer")
@Getter
@Setter
public class QuizAnswer {

    @Id
    @SequenceGenerator(name = "quiz_answer_seq_gen", sequenceName = "quiz_answer_seq", allocationSize = 1)
    @GeneratedValue(generator = "quiz_answer_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_true", nullable = false)
    private Boolean isTrue;

}
