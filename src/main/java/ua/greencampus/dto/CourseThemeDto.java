package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ua.greencampus.common.Messages;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class CourseThemeDto {
    private Long id;
    @NotEmpty(message = Messages.THEME_NAME_EMPTY)
    private String name;
    private List<String> files;
}
