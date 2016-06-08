package ua.greencampus.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseTheme getCourseTheme() {
        return courseTheme;
    }

    public void setCourseTheme(CourseTheme courseTheme) {
        this.courseTheme = courseTheme;
    }

    public List<QuizQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizQuestion> questions) {
        this.questions = questions;
    }
}
