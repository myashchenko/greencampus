package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Ivan Mikho, created on 18.05.16.
 */
@Entity
@Table(name = "user_course")
@Getter
@Setter
public class UserCourse {

    @Id
    @SequenceGenerator(name = "user_course_seq_gen", sequenceName = "user_course_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_course_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserCourseRole userRole;

    public UserCourse() {
    }

    public UserCourse(User user, Course course, UserCourseRole userCourseRole) {
        this.user = user;
        this.course = course;
        this.userRole = userCourseRole;
    }
}
