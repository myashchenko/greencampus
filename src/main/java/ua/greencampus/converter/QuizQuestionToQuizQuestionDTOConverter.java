package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDTO;
import ua.greencampus.dto.QuizQuestionDTO;
import ua.greencampus.entity.QuizAnswer;
import ua.greencampus.entity.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 30.04.16.
 */
public class QuizQuestionToQuizQuestionDTOConverter implements Converter<QuizQuestion, QuizQuestionDTO> {

    private ConversionService conversionService;

    public QuizQuestionToQuizQuestionDTOConverter (ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    @Override
    public QuizQuestionDTO convert(QuizQuestion quizQuestion) {
        if (quizQuestion == null) {
            return null;
        }

        QuizQuestionDTO quizQuestionDTO = new QuizQuestionDTO();
        quizQuestionDTO.setId(quizQuestion.getId());
        quizQuestionDTO.setQuestion(quizQuestion.getQuestion());
        if (quizQuestion.getAnswers().size() > 0) {
            List<QuizAnswerDTO> quizAnswerDTOList = new ArrayList<>();
            for (QuizAnswer quizAnswer:quizQuestion.getAnswers()){
                quizAnswerDTOList.add(conversionService.convert(quizAnswer,QuizAnswerDTO.class));
            }
            quizQuestionDTO.setQuizAnswerDTOList(quizAnswerDTOList);
        }
        return quizQuestionDTO;
    }
}
