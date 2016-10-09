package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class BaseResponse {
    private List<String> messages;

    public BaseResponse() {
        this.messages = new ArrayList<>();
    }

    public BaseResponse(String... messages) {
        this();
        Stream.of(messages).forEach(this::putMessage);
    }

    public BaseResponse(Errors errors) {
        this();
        putMessages(errors);
    }

    public void putMessages(Errors errors) {
        errors.getAllErrors().forEach(e -> putMessage(e.getDefaultMessage()));
    }

    public void putMessage(String value) {
        if (value != null) {
            messages.add(value);
        }
    }
}
