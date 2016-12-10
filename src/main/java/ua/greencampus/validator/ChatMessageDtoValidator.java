package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.ChatMessageDto;

/**
 * @author Nikolay Yashchenko
 */
@Component("chatMessageDtoValidator")
public class ChatMessageDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChatMessageDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ChatMessageDto chatMessageDto = (ChatMessageDto) target;
        if (chatMessageDto.getText() == null) {
            errors.reject("bad_text", "text mustn't be empty");
        }

        if (chatMessageDto.getDialogId() == null) {
            errors.reject("bad_dialog_id", "dialog id must be > 0");
        }
    }
}
