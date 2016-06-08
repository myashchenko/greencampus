package ua.greencampus.entity;

import javax.persistence.*;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "quiz_answer")
public class QuizAnswer {

    @Id
    @SequenceGenerator(name = "quiz_answer_seq_gen", sequenceName = "quiz_answer_seq", allocationSize = 1)
    @GeneratedValue(generator = "quiz_answer_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_true", nullable = false)
    private Boolean isTrue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }
}
