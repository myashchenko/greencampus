package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.CourseThemeDto;


/**
 * Created by Ivan Mikho on 10.04.16.
 */
@Component("themeDtoValidator")
public class ThemeDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseThemeDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseThemeDto themeDto = (CourseThemeDto) o;
        if (themeDto.getName() == null || themeDto.getName().isEmpty()) {
            errors.rejectValue("bad_name", "name mustn't be empty");
        }
    }
}
