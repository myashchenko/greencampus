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
public class FileEntity extends BaseEntity {

    @Column(name = "path", nullable = false, unique = true)
    private String path;

    @Column(name = "name")
    private String name;

}
