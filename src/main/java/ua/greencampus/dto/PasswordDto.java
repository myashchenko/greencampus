package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.greencampus.common.Messages;

import javax.validation.constraints.Size;

/**
 * Created by Arsenii on 22.05.2016.
 */
@Getter
@Setter
public class PasswordDto {
    private Long id;
    private String oldPassword;
    @Size(min = 5, message = Messages.PASSWORD_INCORRECT)
    @NotEmpty(message = Messages.PASSWORD_EMPTY)
    private String newPassword;
}
