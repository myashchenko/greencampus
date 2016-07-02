package ua.greencampus.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDto;
import ua.greencampus.dto.QuizQuestionDto;
import ua.greencampus.entity.QuizAnswer;
import ua.greencampus.entity.QuizQuestion;
import ua.greencampus.service.QuizQuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mikho
 */

public class QuizQuestionDtoToQuizQuestionConverter implements Converter<QuizQuestionDto, QuizQuestion>{

    private QuizQuestionService quizQuestionService;
    private ConversionService conversionService;

    public QuizQuestionDtoToQuizQuestionConverter(QuizQuestionService quizQuestionService, ConversionService conversionService){
        this.quizQuestionService = quizQuestionService;
        this.conversionService = conversionService;
    }

    @Override
    public QuizQuestion convert(QuizQuestionDto quizQuestionDto) {
        QuizQuestion quizQuestion = null;
        Long quizQuestionId = quizQuestionDto.getId();

        if (quizQuestionId != null){
            quizQuestion = quizQuestionService.read(quizQuestionId);
        }

        if (quizQuestion == null) {
            quizQuestion = new QuizQuestion();
        }

        quizQuestion.setQuestion(quizQuestionDto.getQuestion());

        if (quizQuestionDto.getQuizAnswers().size() > 0) {
            List<QuizAnswer> quizAnswerList = new ArrayList<>();
            for (QuizAnswerDto quizAnswerDto:quizQuestionDto.getQuizAnswers()){
                quizAnswerList.add(conversionService.convert(quizAnswerDto, QuizAnswer.class));
            }
            quizQuestion.setAnswers(quizAnswerList);
        }
        return quizQuestion;
    }
}
