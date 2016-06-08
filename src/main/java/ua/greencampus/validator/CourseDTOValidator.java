package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.CourseDTO;

/**
 * @author Nikolay Yashchenko
 */
@Component("courseDTOValidator")
public class CourseDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseDTO courseDTO = (CourseDTO) o;

        if (courseDTO.getTitle() == null || courseDTO.getTitle().isEmpty()) {
            errors.rejectValue("bad_title", "title mustn't be empty");
        }
    }
}
