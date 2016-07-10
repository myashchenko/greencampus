package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class ErrorDto {
    private String key;
    private String value;

    public ErrorDto() {
    }

    public ErrorDto(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
