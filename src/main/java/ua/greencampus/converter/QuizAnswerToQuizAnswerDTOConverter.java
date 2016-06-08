package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDTO;
import ua.greencampus.entity.QuizAnswer;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
public class QuizAnswerToQuizAnswerDTOConverter implements Converter<QuizAnswer, QuizAnswerDTO>{
    @Override
    public QuizAnswerDTO convert(QuizAnswer quizAnswer){
        if (quizAnswer == null){
            return null;
        }

        QuizAnswerDTO quizAnswerDTO = new QuizAnswerDTO();

        quizAnswerDTO.setId(quizAnswer.getId());
        quizAnswerDTO.setName(quizAnswer.getName());
        quizAnswerDTO.setTrue(quizAnswer.getTrue());

        return quizAnswerDTO;
    }
}