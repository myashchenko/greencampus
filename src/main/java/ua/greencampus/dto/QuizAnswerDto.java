package ua.greencampus.dto;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
public class QuizAnswerDto {
    private Long id;
    private String name;
    private Boolean isTrue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getTrue() {
        return isTrue;
    }

    public void setTrue(Boolean aTrue) {
        isTrue = aTrue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
