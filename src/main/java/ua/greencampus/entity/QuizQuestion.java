package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz_question")
@Getter
@Setter
public class QuizQuestion {

    @Id
    @SequenceGenerator(name = "quiz_question_seq_gen", sequenceName = "quiz_question_seq", allocationSize = 1)
    @GeneratedValue(generator = "quiz_question_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", nullable = false)
    private List<QuizAnswer> answers;

}
