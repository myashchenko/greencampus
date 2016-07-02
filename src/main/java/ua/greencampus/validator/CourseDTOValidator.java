package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.CourseDto;

/**
 * @author Nikolay Yashchenko
 */
@Component("courseDTOValidator")
public class CourseDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CourseDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseDto courseDTO = (CourseDto) o;

        if (courseDTO.getTitle() == null || courseDTO.getTitle().isEmpty()) {
            errors.rejectValue("bad_title", "title mustn't be empty");
        }
    }
}
