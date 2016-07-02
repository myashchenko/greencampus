package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDto;
import ua.greencampus.dto.QuizQuestionDto;
import ua.greencampus.entity.QuizAnswer;
import ua.greencampus.entity.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan Mikho on 30.04.16.
 */
public class QuizQuestionToQuizQuestionDtoConverter implements Converter<QuizQuestion, QuizQuestionDto> {

    private ConversionService conversionService;

    public QuizQuestionToQuizQuestionDtoConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    @Override
    public QuizQuestionDto convert(QuizQuestion quizQuestion) {
        if (quizQuestion == null) {
            return null;
        }

        QuizQuestionDto quizQuestionDto = new QuizQuestionDto();
        quizQuestionDto.setId(quizQuestion.getId());
        quizQuestionDto.setQuestion(quizQuestion.getQuestion());
        if (quizQuestion.getAnswers().size() > 0) {
            List<QuizAnswerDto> quizAnswerDtoList = new ArrayList<>();
            for (QuizAnswer quizAnswer:quizQuestion.getAnswers()){
                quizAnswerDtoList.add(conversionService.convert(quizAnswer,QuizAnswerDto.class));
            }
            quizQuestionDto.setQuizAnswers(quizAnswerDtoList);
        }
        return quizQuestionDto;
    }
}
