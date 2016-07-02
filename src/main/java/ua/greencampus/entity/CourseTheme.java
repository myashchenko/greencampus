package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "course_theme")
@Getter
@Setter
public class CourseTheme {

    @Id
    @SequenceGenerator(name = "course_theme_seq_gen", sequenceName = "course_theme_seq", allocationSize = 1)
    @GeneratedValue(generator = "course_theme_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "lecture_file",
            joinColumns = @JoinColumn(name = "lecture_id"), inverseJoinColumns = @JoinColumn(name = "file_id"))
    private List<FileEntity> files;

}
