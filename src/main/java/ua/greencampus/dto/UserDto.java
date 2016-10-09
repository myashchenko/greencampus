package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.greencampus.common.Messages;

import javax.validation.constraints.Size;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class UserDto {

    private Long id;
    @NotEmpty(message = Messages.EMAIL_EMPTY)
    private String email;
    @NotEmpty(message = Messages.PASSWORD_EMPTY)
    @Size(min = 5, message = Messages.PASSWORD_TOO_SHORT)
    private String password;
    private String name;
    private String role;
    private FileDto avatar;
}
