package ua.greencampus.dto;

import java.util.List;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
public class QuizQuestionDto {
    private Long id;
    private String question;
    private List<QuizAnswerDto> quizAnswers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuizAnswerDto> getQuizAnswers() {
        return quizAnswers;
    }

    public void setQuizAnswers(List<QuizAnswerDto> quizAnswers) {
        this.quizAnswers = quizAnswers;
    }
}
