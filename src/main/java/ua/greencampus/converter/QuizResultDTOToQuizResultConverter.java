package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizResultDTO;
import ua.greencampus.entity.Quiz;
import ua.greencampus.entity.QuizResult;
import ua.greencampus.entity.User;
import ua.greencampus.service.QuizResultService;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizResultDTOToQuizResultConverter implements Converter<QuizResultDTO, QuizResult> {

    private QuizResultService quizResultService;
    private ConversionService conversionService;

    public QuizResultDTOToQuizResultConverter(QuizResultService quizResultService, ConversionService conversionService) {
        this.quizResultService = quizResultService;
        this.conversionService = conversionService;
    }

    @Override
    public QuizResult convert(QuizResultDTO quizResultDTO) {

        QuizResult quizResult = null;
        Long quizId = quizResultDTO.getId();

        if (quizId != null) {
            quizResult = quizResultService.read(quizId);
        }

        if (quizResult == null) {
            quizResult = new QuizResult();
        }

        quizResult.setScore(quizResultDTO.getScore());
        quizResult.setQuiz(conversionService.convert(quizResultDTO.getQuizDTO(), Quiz.class));
        quizResult.setUser(conversionService.convert(quizResultDTO.getUserDTO(), User.class));

        return quizResult;
    }
}
