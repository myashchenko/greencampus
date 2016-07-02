package ua.greencampus.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.greencampus.dto.ChatDialogDto;

/**
 * @author Nikolay Yashchenko
 */
@Component("chatDialogDTOValidator")
public class ChatDialogDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChatDialogDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChatDialogDto chatDialogDTO = (ChatDialogDto) target;
        if (chatDialogDTO.getUsersIds() == null || chatDialogDTO.getUsersIds().size() < 2) {
            errors.reject("bad_users_ids", "count of user must be equal or greater than 2");
        }
    }
}
