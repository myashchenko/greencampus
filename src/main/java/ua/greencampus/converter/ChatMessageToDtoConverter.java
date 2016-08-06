package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatMessageDto;
import ua.greencampus.entity.ChatMessage;
import ua.greencampus.entity.FileEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * @author Nikolay Yashchenko
 */
public class ChatMessageToDtoConverter implements Converter<ChatMessage, ChatMessageDto> {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public ChatMessageDto convert(ChatMessage chatMessage) {
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setId(chatMessage.getId());
        chatMessageDto.setText(chatMessage.getText());

        chatMessageDto.setUserFromName(chatMessage.getUserFrom().getName());
        chatMessageDto.setUserFromId(chatMessage.getUserFrom().getId());
        chatMessageDto.setDate(dateFormat.format(chatMessage.getSendDate().getTime()));
        chatMessageDto.setAvatarPath(Optional.ofNullable(chatMessage.getUserFrom().getAvatar())
                .map(FileEntity::getPath).orElse(null));

        return chatMessageDto;
    }
}
