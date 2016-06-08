package ua.greencampus.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz_question")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswer> answers) {
        this.answers = answers;
    }
}
