package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "faculty")
@Getter
@Setter
public class Faculty {

    @Id
    @SequenceGenerator(name = "faculty_seq_gen", sequenceName = "faculty_seq", allocationSize = 1)
    @GeneratedValue(generator = "faculty_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "faculties")
    private List<Speciality> specialities;

    @OneToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FileEntity emblem;

}
