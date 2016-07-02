package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDto;
import ua.greencampus.entity.QuizAnswer;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
public class QuizAnswerToQuizAnswerDtoConverter implements Converter<QuizAnswer, QuizAnswerDto> {

    @Override
    public QuizAnswerDto convert(QuizAnswer quizAnswer) {
        if (quizAnswer == null) {
            return null;
        }

        QuizAnswerDto quizAnswerDto = new QuizAnswerDto();

        quizAnswerDto.setId(quizAnswer.getId());
        quizAnswerDto.setName(quizAnswer.getName());
        quizAnswerDto.setTrue(quizAnswer.getTrue());

        return quizAnswerDto;
    }
}