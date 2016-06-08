package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDTO;
import ua.greencampus.dto.QuizDTO;
import ua.greencampus.dto.QuizQuestionDTO;
import ua.greencampus.entity.Quiz;
import ua.greencampus.entity.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizToQuizDTOConverter implements Converter<Quiz, QuizDTO> {

    private ConversionService conversionService;

    public QuizToQuizDTOConverter (ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    @Override
    public QuizDTO convert(Quiz quiz) {
        if (quiz == null) {
            return null;
        }

        QuizDTO quizDTO = new QuizDTO();

        quizDTO.setId(quiz.getId());
        if (quiz.getQuestions().size() > 0) {
            List<QuizQuestionDTO> quizQuestionDTOList = new ArrayList<>();
            for (QuizQuestion quizQuestion:quiz.getQuestions()) {
                quizQuestionDTOList.add(conversionService.convert(quizQuestion, QuizQuestionDTO.class));
            }
            quizDTO.setQuizQuestionDTOList(quizQuestionDTOList);
        }

        if (quiz.getCourseTheme() != null) {
            quizDTO.setCourseThemeDTO(conversionService.convert(quiz.getCourseTheme(), CourseThemeDTO.class));
        }

        return quizDTO;
    }
}
