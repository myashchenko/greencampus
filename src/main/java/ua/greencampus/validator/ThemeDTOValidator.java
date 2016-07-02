package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.CourseThemeDto;


/**
 * Created by Ivan Mikho on 10.04.16.
 */
@Component("themeDTOValidator")
public class ThemeDTOValidator  implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return CourseThemeDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseThemeDto themeDTO = (CourseThemeDto) o;
        if (themeDTO.getName() == null || themeDTO.getName().isEmpty()) {
            errors.rejectValue("bad_name", "name mustn't be empty");
        }
    }
}
