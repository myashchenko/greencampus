package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatMessageDTO;
import ua.greencampus.entity.ChatMessage;
import ua.greencampus.entity.FileEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Optional;

/**
 * @author Nikolay Yashchenko
 */
public class ChatMessageToDTOConverter implements Converter<ChatMessage, ChatMessageDTO> {

    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public ChatMessageDTO convert(ChatMessage chatMessage) {
        ChatMessageDTO chatMessageDTO = new ChatMessageDTO();
        chatMessageDTO.setId(chatMessage.getId());
        chatMessageDTO.setText(chatMessage.getText());

        chatMessageDTO.setUserFromName(chatMessage.getUserFrom().getName());
        chatMessageDTO.setUserFromId(chatMessage.getUserFrom().getId());
        chatMessageDTO.setDate(dateFormat.format(chatMessage.getSendDate().getTime()));
        chatMessageDTO.setAvatarPath(Optional.ofNullable(chatMessage.getUserFrom().getAvatar()).map(FileEntity::getPath).orElse(null));

        return chatMessageDTO;
    }
}
