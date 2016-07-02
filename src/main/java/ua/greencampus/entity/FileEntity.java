package ua.greencampus.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "file")
@Getter
@Setter
public class FileEntity {
    @Id
    @SequenceGenerator(name = "file_seq_gen", sequenceName = "file_seq", allocationSize = 1)
    @GeneratedValue(generator = "file_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "path", nullable = false, unique = true)
    private String path;

    @Column(name = "name")
    private String name;

}
