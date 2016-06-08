package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.CourseThemeDTO;


/**
 * Created by Ivan Mikho on 10.04.16.
 */
@Component("themeDTOValidator")
public class ThemeDTOValidator  implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return CourseThemeDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseThemeDTO themeDTO = (CourseThemeDTO) o;
        if (themeDTO.getName() == null || themeDTO.getName().isEmpty()) {
            errors.rejectValue("bad_name", "name mustn't be empty");
        }
    }
}
