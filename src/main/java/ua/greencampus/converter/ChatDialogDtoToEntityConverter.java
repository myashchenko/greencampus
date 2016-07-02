package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatDialogDto;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
public class ChatDialogDtoToEntityConverter implements Converter<ChatDialogDto, ChatDialog> {

    private UserService userService;

    public ChatDialogDtoToEntityConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ChatDialog convert(ChatDialogDto chatDialogDto) {
        if (chatDialogDto == null) return null;

        ChatDialog chatDialog = new ChatDialog();
        Set<User> userSet = chatDialogDto.getUsersIds().stream()
                .map(userService::read)
                .collect(Collectors.toSet());
        chatDialog.setUsers(userSet);

        return chatDialog;
    }
}
