package ua.greencampus.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by Arsenii on 21.03.2016.
 */
@Entity
@Table(name = "users")
@DynamicUpdate
@Getter
@Setter
@EqualsAndHashCode(of = {"email"}, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, updatable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", unique = true)
    private FileEntity avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, updatable = false)
    private Role role;
}
