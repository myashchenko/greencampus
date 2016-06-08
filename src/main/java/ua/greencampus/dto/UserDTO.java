package ua.greencampus.dto;

import ua.greencampus.entity.FileEntity;
import ua.greencampus.entity.Role;

/**
 * @author Nikolay Yashchenko
 */
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    private FileDTO avatar;

    public UserDTO() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvatar(FileDTO avatar) {
        this.avatar = avatar;
    }

    public FileDTO getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
