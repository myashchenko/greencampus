package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "speciality")
@Getter
@Setter
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "speciality_faculty",
            joinColumns = @JoinColumn(name = "speciality_id"), inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private List<Faculty> faculties;

}
