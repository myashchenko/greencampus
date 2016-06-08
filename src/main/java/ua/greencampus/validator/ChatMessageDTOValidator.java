package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.ChatMessageDTO;

/**
 * @author Nikolay Yashchenko
 */
@Component("chatMessageDTOValidator")
public class ChatMessageDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChatMessageDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ChatMessageDTO chatMessageDTO = (ChatMessageDTO) target;
        if (chatMessageDTO.getText() == null) {
            errors.reject("bad_text", "text mustn't be empty");
        }

        if (chatMessageDTO.getDialogId() == null || chatMessageDTO.getDialogId() <= 0L) {
            errors.reject("bad_dialog_id", "dialog id must be > 0");
        }
    }
}
