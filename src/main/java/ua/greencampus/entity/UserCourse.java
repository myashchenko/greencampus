package ua.greencampus.entity;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserCourse extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserCourseRole userRole;
}
