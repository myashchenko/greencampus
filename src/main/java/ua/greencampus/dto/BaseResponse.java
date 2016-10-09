package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class BaseResponse {
    private List<ErrorDto> errors;

    public BaseResponse() {
        this.errors = new ArrayList<>();
    }

    public BaseResponse(Errors errors) {
        this();
        putErrors(errors);
    }

    public void putErrors(Errors errors) {
        errors.getAllErrors().forEach(e -> putError(e.getCode(), e.getDefaultMessage()));
    }

    public void putError(String key, String value) {
        errors.add(new ErrorDto(key, value));
    }
}
