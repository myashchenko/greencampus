package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz")
@Getter
@Setter
public class Quiz {

    @Id
    @SequenceGenerator(name = "quiz_seq_gen", sequenceName = "quiz_seq", allocationSize = 1)
    @GeneratedValue(generator = "quiz_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private CourseTheme courseTheme;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<QuizQuestion> questions;

}
