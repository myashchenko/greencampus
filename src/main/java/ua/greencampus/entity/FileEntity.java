package ua.greencampus.entity;

import javax.persistence.*;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @SequenceGenerator(name = "file_seq_gen", sequenceName = "file_seq", allocationSize = 1)
    @GeneratedValue(generator = "file_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "path", nullable = false, unique = true)
    private String path;

    @Column(name = "name")
    private String name;

    public FileEntity(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
