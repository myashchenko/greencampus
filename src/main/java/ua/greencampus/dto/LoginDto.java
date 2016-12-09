package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class LoginDto {
    private String email;
    private String password;
}
