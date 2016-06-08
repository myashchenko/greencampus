package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.UserDTO;

/**
 * @author Nikolay Yashchenko
 */
@Component("userDTOValidator")
public class UserDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO userDTO = (UserDTO) o;
        if (userDTO.getEmail() == null || userDTO.getEmail().isEmpty()) {
            errors.rejectValue("bad_email", "email mustn't be empty");
        }

        if (userDTO.getPassword() == null || userDTO.getPassword().length() < 5) {
            errors.rejectValue("bad_password", "password size must be > 5");
        }
    }
}
