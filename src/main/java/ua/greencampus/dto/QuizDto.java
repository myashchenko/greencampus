package ua.greencampus.dto;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public class QuizDto {
    private Long id;
    private CourseThemeDto courseTheme;
    private List<QuizQuestionDto> quizQuestions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseThemeDto getCourseTheme() {
        return courseTheme;
    }

    public void setCourseTheme(CourseThemeDto courseTheme) {
        this.courseTheme = courseTheme;
    }

    public List<QuizQuestionDto> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestionDto> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }
}
