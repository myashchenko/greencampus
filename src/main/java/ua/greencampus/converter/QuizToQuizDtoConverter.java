package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.CourseThemeDto;
import ua.greencampus.dto.QuizDto;
import ua.greencampus.dto.QuizQuestionDto;
import ua.greencampus.entity.Quiz;
import ua.greencampus.entity.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizToQuizDtoConverter implements Converter<Quiz, QuizDto> {

    private ConversionService conversionService;

    public QuizToQuizDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    @Override
    public QuizDto convert(Quiz quiz) {
        if (quiz == null) {
            return null;
        }

        QuizDto quizDto = new QuizDto();

        quizDto.setId(quiz.getId());
        if (quiz.getQuestions().size() > 0) {
            List<QuizQuestionDto> quizQuestionDtoList = new ArrayList<>();
            for (QuizQuestion quizQuestion:quiz.getQuestions()) {
                quizQuestionDtoList.add(conversionService.convert(quizQuestion, QuizQuestionDto.class));
            }
            quizDto.setQuizQuestions(quizQuestionDtoList);
        }

        if (quiz.getCourseTheme() != null) {
            quizDto.setCourseTheme(conversionService.convert(quiz.getCourseTheme(), CourseThemeDto.class));
        }

        return quizDto;
    }
}
