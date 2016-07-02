package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.UserDto;

/**
 * Created by Arsenii on 21.05.2016.
 */
@Component("emailDTOValidator")
public class EmailDTOValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return UserDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDto userDTO = (UserDto) o;
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            errors.rejectValue("bad_email", "email mustn't be empty");
        }
    }
}
