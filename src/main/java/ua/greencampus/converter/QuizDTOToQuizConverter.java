package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizDTO;
import ua.greencampus.dto.QuizQuestionDTO;
import ua.greencampus.entity.CourseTheme;
import ua.greencampus.entity.Quiz;
import ua.greencampus.entity.QuizQuestion;
import ua.greencampus.service.QuizService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
public class QuizDTOToQuizConverter implements Converter<QuizDTO,Quiz> {

    private QuizService quizService;
    private ConversionService conversionService;

    public QuizDTOToQuizConverter (QuizService quizService, ConversionService conversionService) {

        this.quizService = quizService;
        this.conversionService = conversionService;

    }

    @Override
    public Quiz convert(QuizDTO quizDTO){

        Quiz quiz = null;
        Long quizId = quizDTO.getId();

        if (quizId != null) {
            quiz = quizService.read(quizId);
        }

        if (quiz == null) {
            quiz = new Quiz();
        }

        if (quizDTO.getCourseThemeDTO() != null) {
            quiz.setCourseTheme(conversionService.convert(quizDTO.getCourseThemeDTO(), CourseTheme.class));
        }

        if (quizDTO.getQuizQuestionDTOList().size() > 0){
            List<QuizQuestion> quizQuestionList = new ArrayList<>();
            for (QuizQuestionDTO quizQuestionDTO:quizDTO.getQuizQuestionDTOList()) {
                quizQuestionList.add(conversionService.convert(quizQuestionDTO,QuizQuestion.class));
            }
            quiz.setQuestions(quizQuestionList);
        }

        return quiz;
    }
}
