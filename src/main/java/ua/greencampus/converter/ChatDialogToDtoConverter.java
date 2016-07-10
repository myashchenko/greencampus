package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatDialogDto;
import ua.greencampus.entity.ChatDialog;

/**
 * @author Nikolay Yashchenko
 */
public class ChatDialogToDtoConverter implements Converter<ChatDialog, ChatDialogDto> {

    @Override
    public ChatDialogDto convert(ChatDialog chatDialog) {
        if (chatDialog == null) {
            return null;
        }

        ChatDialogDto chatDialogDto = new ChatDialogDto();
        chatDialogDto.setId(chatDialog.getId());
        chatDialogDto.setDialogName(chatDialog.getDialogName());
        chatDialogDto.setUnreadCount(chatDialog.getUnreadCount());
        chatDialogDto.setAvatarPath(chatDialog.getAvatarPath());

        return chatDialogDto;
    }
}
