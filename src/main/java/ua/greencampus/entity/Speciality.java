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
    @SequenceGenerator(name = "speciality_seq_gen", sequenceName = "speciality_seq", allocationSize = 1)
    @GeneratedValue(generator = "speciality_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "speciality_faculty",
            joinColumns = @JoinColumn(name = "speciality_id"), inverseJoinColumns = @JoinColumn(name = "faculty_id"))
    private List<Faculty> faculties;

}
