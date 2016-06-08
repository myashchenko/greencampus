package ua.greencampus.dto;

import java.util.List;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
public class QuizQuestionDTO {
    private Long id;
    private String question;
    private List<QuizAnswerDTO> quizAnswerDTOList;

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

    public List<QuizAnswerDTO> getQuizAnswerDTOList() {
        return quizAnswerDTOList;
    }

    public void setQuizAnswerDTOList(List<QuizAnswerDTO> quizAnswerDTOList) {
        this.quizAnswerDTOList = quizAnswerDTOList;
    }
}
