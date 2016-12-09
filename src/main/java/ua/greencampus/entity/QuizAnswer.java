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
public class QuizAnswer extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_true", nullable = false)
    private Boolean isTrue;

}
