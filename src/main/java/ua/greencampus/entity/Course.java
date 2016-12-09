package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "course")
@NamedEntityGraph(name = "course.themes", attributeNodes = @NamedAttributeNode("themes"))
@Getter
@Setter
public class Course extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "course_id", nullable = false)
    @OrderBy("id asc")
    private List<CourseTheme> themes;

}
