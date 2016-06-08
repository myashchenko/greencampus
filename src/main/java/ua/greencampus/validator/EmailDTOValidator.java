package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.UserDTO;

/**
 * Created by Arsenii on 21.05.2016.
 */
@Component("emailDTOValidator")
public class EmailDTOValidator implements Validator{
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
    }
}
