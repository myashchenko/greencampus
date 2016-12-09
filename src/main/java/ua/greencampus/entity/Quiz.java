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
public class Quiz extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    private CourseTheme courseTheme;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<QuizQuestion> questions;

}
