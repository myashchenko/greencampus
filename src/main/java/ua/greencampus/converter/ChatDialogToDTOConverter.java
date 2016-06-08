package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatDialogDTO;
import ua.greencampus.entity.ChatDialog;

/**
 * @author Nikolay Yashchenko
 */
public class ChatDialogToDTOConverter implements Converter<ChatDialog, ChatDialogDTO> {

    @Override
    public ChatDialogDTO convert(ChatDialog chatDialog) {
        if (chatDialog == null) return null;

        ChatDialogDTO chatDialogDTO = new ChatDialogDTO();
        chatDialogDTO.setId(chatDialog.getId());
        chatDialogDTO.setDialogName(chatDialog.getDialogName());
        chatDialogDTO.setUnreadCount(chatDialog.getUnreadCount());
        chatDialogDTO.setAvatarPath(chatDialog.getAvatarPath());

        return chatDialogDTO;
    }
}
