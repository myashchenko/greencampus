package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class QuizDto {
    private Long id;
    private CourseThemeDto courseTheme;
    private List<QuizQuestionDto> quizQuestions;
}
