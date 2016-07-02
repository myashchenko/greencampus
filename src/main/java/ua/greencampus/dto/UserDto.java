package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String role;
    private FileDto avatar;
}
