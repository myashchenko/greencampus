package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import ua.greencampus.entity.BaseEntity;

/**
 * Created by Ivan Mikho on 16.04.16.
 */
@Getter
@Setter
public class QuizAnswerDto extends BaseEntity {
    private String name;
    private Boolean isTrue;
}
