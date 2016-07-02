package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.PasswordDto;

/**
 * Created by Arsenii on 21.05.2016.
 */
@Component("passwordDtoValidator")
public class PasswordDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PasswordDto passwordDto = (PasswordDto) o;
        if (passwordDto.getNewPassword() == null || passwordDto.getNewPassword().length() < 5) {
            errors.rejectValue("bad_password", "password size must be > 5");
        }

    }
}
