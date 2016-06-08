package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Nikolay Yashchenko
 */
@Component("idValidator")
public class IdValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Long.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Long id = (Long) o;

        if (id <= 0) {
            errors.rejectValue("bad_id", "id must be > 0");
        }
    }
}
