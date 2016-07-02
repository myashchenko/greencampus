package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizResultDto;
import ua.greencampus.entity.Quiz;
import ua.greencampus.entity.QuizResult;
import ua.greencampus.entity.User;
import ua.greencampus.service.QuizResultService;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizResultDtoToQuizResultConverter implements Converter<QuizResultDto, QuizResult> {

    private QuizResultService quizResultService;
    private ConversionService conversionService;

    public QuizResultDtoToQuizResultConverter(QuizResultService quizResultService, ConversionService conversionService) {
        this.quizResultService = quizResultService;
        this.conversionService = conversionService;
    }

    @Override
    public QuizResult convert(QuizResultDto quizResultDto) {

        QuizResult quizResult = null;
        Long quizId = quizResultDto.getId();

        if (quizId != null) {
            quizResult = quizResultService.read(quizId);
        }

        if (quizResult == null) {
            quizResult = new QuizResult();
        }

        quizResult.setScore(quizResultDto.getScore());
        quizResult.setQuiz(conversionService.convert(quizResultDto.getQuiz(), Quiz.class));
        quizResult.setUser(conversionService.convert(quizResultDto.getUser(), User.class));

        return quizResult;
    }
}
