package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.greencampus.common.Messages;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class CourseDto extends BaseDto {
    private Long id;
    @NotEmpty(message = Messages.COURSE_TITLE_EMPTY)
    private String title;
    private String description;
    private String author;
}
