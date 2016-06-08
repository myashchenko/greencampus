package ua.greencampus.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "faculty")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }

    public FileEntity getEmblem() {
        return emblem;
    }

    public void setEmblem(FileEntity emblem) {
        this.emblem = emblem;
    }
}
