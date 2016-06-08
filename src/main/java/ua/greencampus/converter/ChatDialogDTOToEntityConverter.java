package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatDialogDTO;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
public class ChatDialogDTOToEntityConverter implements Converter<ChatDialogDTO, ChatDialog> {

    private UserService userService;

    public ChatDialogDTOToEntityConverter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ChatDialog convert(ChatDialogDTO chatDialogDTO) {
        if (chatDialogDTO == null) return null;

        ChatDialog chatDialog = new ChatDialog();
        Set<User> userSet = chatDialogDTO.getUsersIds().stream()
                .map(userService::read)
                .collect(Collectors.toSet());
        chatDialog.setUsers(userSet);

        return chatDialog;
    }
}
