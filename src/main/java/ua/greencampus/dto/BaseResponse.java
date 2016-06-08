package ua.greencampus.dto;

import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public class BaseResponse {
    private List<ErrorDTO> errors;

    public BaseResponse() {
        this.errors = new ArrayList<>();
    }

    public BaseResponse(Errors errors) {
        this();
        putErrors(errors);
    }

    public void putErrors(Errors errors) {
        errors.getAllErrors().stream()
                .forEach(e -> putError(e.getCode(), e.getDefaultMessage()));
    }

    public void putError(String key, String value) {
        errors.add(new ErrorDTO(key, value));
    }

    public List<ErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDTO> errors) {
        this.errors = errors;
    }
}
