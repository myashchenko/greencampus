package ua.greencampus.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDTO;
import ua.greencampus.dto.QuizQuestionDTO;
import ua.greencampus.entity.QuizAnswer;
import ua.greencampus.entity.QuizQuestion;
import ua.greencampus.service.QuizAnswerService;
import ua.greencampus.service.QuizQuestionService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Mikho
 */

public class QuizQuestionDTOToQuizQuestionConverter implements Converter<QuizQuestionDTO, QuizQuestion>{

    private QuizQuestionService quizQuestionService;
    private ConversionService conversionService;

    public QuizQuestionDTOToQuizQuestionConverter(QuizQuestionService quizQuestionService, ConversionService conversionService){
        this.quizQuestionService = quizQuestionService;
        this.conversionService = conversionService;
    }

    @Override
    public QuizQuestion convert(QuizQuestionDTO quizQuestionDTO) {
        QuizQuestion quizQuestion = null;
        Long quizQuestionId = quizQuestionDTO.getId();

        if (quizQuestionId != null){
            quizQuestion = quizQuestionService.read(quizQuestionId);
        }

        if (quizQuestion == null) {
            quizQuestion = new QuizQuestion();
        }

        quizQuestion.setQuestion(quizQuestionDTO.getQuestion());

        if (quizQuestionDTO.getQuizAnswerDTOList().size() > 0) {
            List<QuizAnswer> quizAnswerList = new ArrayList<>();
            for (QuizAnswerDTO quizAnswerDTO:quizQuestionDTO.getQuizAnswerDTOList()){
                quizAnswerList.add(conversionService.convert(quizAnswerDTO, QuizAnswer.class));
            }
            quizQuestion.setAnswers(quizAnswerList);
        }
        return quizQuestion;
    }
}
