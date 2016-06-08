package ua.greencampus.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDTO;
import ua.greencampus.entity.QuizAnswer;
import ua.greencampus.service.QuizAnswerService;



public class QuizAnswerDTOToQuizAnswerConverter implements Converter<QuizAnswerDTO, QuizAnswer>{

    @Autowired
    QuizAnswerService quizAnswerService;

    public QuizAnswerDTOToQuizAnswerConverter(QuizAnswerService quizAnswerService){
        this.quizAnswerService = quizAnswerService;
    }

    @Override
    public QuizAnswer convert(QuizAnswerDTO quizAnswerDTO) {
        QuizAnswer quizAnswer = null;
        Long quizAnswerId = quizAnswerDTO.getId();

        if (quizAnswerId != null){
            quizAnswer = quizAnswerService.read(quizAnswerId);
        }

        if (quizAnswer == null) {
            quizAnswer = new QuizAnswer();
        }

        quizAnswer.setName(quizAnswerDTO.getName());
        quizAnswer.setTrue(quizAnswerDTO.getTrue());

        return quizAnswer;
    }
}

