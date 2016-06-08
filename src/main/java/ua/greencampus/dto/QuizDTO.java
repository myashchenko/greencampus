package ua.greencampus.dto;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public class QuizDTO {
    private Long id;
    private CourseThemeDTO courseThemeDTO;
    private List<QuizQuestionDTO> quizQuestionDTOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseThemeDTO getCourseThemeDTO() {
        return courseThemeDTO;
    }

    public void setCourseThemeDTO(CourseThemeDTO courseThemeDTO) {
        this.courseThemeDTO = courseThemeDTO;
    }

    public List<QuizQuestionDTO> getQuizQuestionDTOList() {
        return quizQuestionDTOList;
    }

    public void setQuizQuestionDTOList(List<QuizQuestionDTO> quizQuestionDTOList) {
        this.quizQuestionDTOList = quizQuestionDTOList;
    }
}
