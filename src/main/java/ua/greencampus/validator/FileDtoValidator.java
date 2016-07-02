package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.FileDto;

/**
 * Created by Arsenii on 18.04.2016.
 */
@Component("fileDtoValidator")
public class FileDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return FileDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FileDto fileDto = (FileDto) o;
        if (fileDto.getPath() == null || fileDto.getPath().isEmpty()) {
            errors.rejectValue("bad_path", "path mustn't be empty");
        }
    }
}
