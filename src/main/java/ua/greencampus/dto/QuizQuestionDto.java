package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
@Getter
@Setter
public class QuizQuestionDto extends BaseDto {
    private Long id;
    private String question;
    private List<QuizAnswerDto> quizAnswers;
}
