package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
@Getter
@Setter
public class QuizAnswerDto {
    private Long id;
    private String name;
    private Boolean isTrue;
}
