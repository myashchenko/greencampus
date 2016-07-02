package ua.greencampus.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.QuizAnswerDto;
import ua.greencampus.entity.QuizAnswer;
import ua.greencampus.service.QuizAnswerService;


public class QuizAnswerDtoToQuizAnswerConverter implements Converter<QuizAnswerDto, QuizAnswer> {

    @Autowired
    QuizAnswerService quizAnswerService;

    public QuizAnswerDtoToQuizAnswerConverter(QuizAnswerService quizAnswerService) {
        this.quizAnswerService = quizAnswerService;
    }

    @Override
    public QuizAnswer convert(QuizAnswerDto quizAnswerDto) {
        QuizAnswer quizAnswer = null;
        Long quizAnswerId = quizAnswerDto.getId();

        if (quizAnswerId != null) {
            quizAnswer = quizAnswerService.read(quizAnswerId);
        }

        if (quizAnswer == null) {
            quizAnswer = new QuizAnswer();
        }

        quizAnswer.setName(quizAnswerDto.getName());
        quizAnswer.setTrue(quizAnswerDto.getTrue());

        return quizAnswer;
    }
}

