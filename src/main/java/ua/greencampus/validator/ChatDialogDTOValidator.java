package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.ChatDialogDTO;

/**
 * @author Nikolay Yashchenko
 */
@Component("chatDialogDTOValidator")
public class ChatDialogDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChatDialogDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChatDialogDTO chatDialogDTO = (ChatDialogDTO) target;
        if (chatDialogDTO.getUsersIds() == null || chatDialogDTO.getUsersIds().size() < 2) {
            errors.reject("bad_users_ids", "count of user must be equal or greater than 2");
        }
    }
}
