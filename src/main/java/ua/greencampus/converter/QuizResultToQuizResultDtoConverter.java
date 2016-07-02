package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizDto;
import ua.greencampus.dto.QuizResultDto;
import ua.greencampus.dto.UserDto;
import ua.greencampus.entity.QuizResult;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizResultToQuizResultDtoConverter implements Converter<QuizResult, QuizResultDto> {

    private ConversionService conversionService;

    public QuizResultToQuizResultDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public QuizResultDto convert(QuizResult quizResult) {
        if (quizResult == null) {
            return null;
        }

        QuizResultDto quizResultDto = new QuizResultDto();

        quizResultDto.setId(quizResult.getId());
        quizResultDto.setScore(quizResult.getScore());
        quizResultDto.setQuiz(conversionService.convert(quizResult.getQuiz(), QuizDto.class));
        quizResultDto.setUser(conversionService.convert(quizResult.getUser(), UserDto.class));

        return quizResultDto;
    }
}
