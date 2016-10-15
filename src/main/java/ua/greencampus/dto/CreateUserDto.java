package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.greencampus.common.Messages;

import javax.validation.constraints.Size;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public class CreateUserDto extends UserDto {
    @NotEmpty(message = Messages.PASSWORD_EMPTY)
    @Size(min = 5, message = Messages.PASSWORD_TOO_SHORT)
    private String password;
}
