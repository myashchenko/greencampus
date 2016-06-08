package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.PasswordDTO;

/**
 * Created by Arsenii on 21.05.2016.
 */
@Component("passwordDTOValidator")
public class PasswordDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordDTO passwordDTO = (PasswordDTO) o;
        if (passwordDTO.getNewPassword() == null || passwordDTO.getNewPassword().length() < 5) {
            errors.rejectValue("bad_password", "password size must be > 5");
        }

    }
}
