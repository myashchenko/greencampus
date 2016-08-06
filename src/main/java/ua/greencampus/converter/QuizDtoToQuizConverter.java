package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizDto;
import ua.greencampus.dto.QuizQuestionDto;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.entity.Quiz;
import ua.greencampus.entity.QuizQuestion;
import ua.greencampus.service.QuizService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizDtoToQuizConverter implements Converter<QuizDto, Quiz> {

    private QuizService quizService;
    private ConversionService conversionService;

    public QuizDtoToQuizConverter(QuizService quizService, ConversionService conversionService) {
        this.quizService = quizService;
        this.conversionService = conversionService;
    }

    @Override
    public Quiz convert(QuizDto quizDto) {

        Quiz quiz = null;
        Long quizId = quizDto.getId();

        if (quizId != null) {
            quiz = quizService.read(quizId);
        }

        if (quiz == null) {
            quiz = new Quiz();
        }

        if (quizDto.getCourseTheme() != null) {
            quiz.setCourseTheme(conversionService.convert(quizDto.getCourseTheme(), CourseTheme.class));
        }

        if (quizDto.getQuizQuestions().size() > 0) {
            List<QuizQuestion> quizQuestionList = new ArrayList<>();
            for (QuizQuestionDto quizQuestionDto : quizDto.getQuizQuestions()) {
                quizQuestionList.add(conversionService.convert(quizQuestionDto, QuizQuestion.class));
            }
            quiz.setQuestions(quizQuestionList);
        }

        return quiz;
    }
}
