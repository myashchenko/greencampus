package ua.greencampus.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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
@EqualsAndHashCode(of = {"id", "email"})
public class User {

    @Id
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(generator = "user_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", unique = true)
    private FileEntity avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public User(String email, String password, String name, FileEntity avatar, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.avatar = avatar;
        this.role = role;
    }

    public User() {
    }
}
