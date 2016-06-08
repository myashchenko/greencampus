package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Arsenii on 31.03.2016.
 */
@Component("userIdValidator")
public class UserIdValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass){
        return Long.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Long id = (Long) o;

        if (id <= 0){
            errors.reject("bad_id", "id should be > 0");
        }
    }

}
