package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizDTO;
import ua.greencampus.dto.QuizResultDTO;
import ua.greencampus.dto.UserDTO;
import ua.greencampus.entity.QuizResult;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizResultToQuizResultDTOConverter implements Converter<QuizResult, QuizResultDTO> {

    private ConversionService conversionService;

    public QuizResultToQuizResultDTOConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public QuizResultDTO convert(QuizResult quizResult) {
        if (quizResult == null) {
            return null;
        }

        QuizResultDTO quizResultDTO = new QuizResultDTO();

        quizResultDTO.setId(quizResult.getId());
        quizResultDTO.setScore(quizResult.getScore());
        quizResultDTO.setQuizDTO(conversionService.convert(quizResult.getQuiz(), QuizDTO.class));
        quizResultDTO.setUserDTO(conversionService.convert(quizResult.getUser(), UserDTO.class));

        return quizResultDTO;
    }
}
