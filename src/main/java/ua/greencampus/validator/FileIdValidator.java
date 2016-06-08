package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.FileDTO;

/**
 * Created by Arsenii on 18.04.2016.
 */
@Component("fileIdValidator")
public class FileIdValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FileDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Long id = (Long) o;

        if (id <= 0) {
            errors.reject("bad_id", "id should be > 0");
        }
    }
}
