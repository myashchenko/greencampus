package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Arsenii on 22.05.2016.
 */
@Getter
@Setter
public class PasswordDto {
    private Long id;
    private String oldPassword;
    private String newPassword;

    public PasswordDto() {
    }
}
