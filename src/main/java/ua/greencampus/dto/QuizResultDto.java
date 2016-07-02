package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Ivan Mikho, created on 02.05.16.
 */
@Getter
@Setter
public class QuizResultDto {
    private Long id;
    private UserDto user;
    private QuizDto quiz;
    private BigDecimal score;
}
