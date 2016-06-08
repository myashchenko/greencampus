package ua.greencampus.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "course")
@NamedEntityGraph(name = "course.themes", attributeNodes = @NamedAttributeNode("themes"))
public class Course {

    @Id
    @SequenceGenerator(name = "course_seq_gen", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(generator = "course_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "course_id", nullable = false)
    @OrderBy("id asc")
    private List<CourseTheme> themes;

    public Course() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CourseTheme> getThemes() {
        return themes;
    }

    public void setThemes(List<CourseTheme> themes) {
        this.themes = themes;
    }
}
