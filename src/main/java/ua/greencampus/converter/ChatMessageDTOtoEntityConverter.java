package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatMessageDTO;
import ua.greencampus.entity.ChatMessage;
import ua.greencampus.service.ChatDialogService;
import ua.greencampus.service.ChatMessageService;
import ua.greencampus.service.UserService;

import java.util.Calendar;

/**
 * @author Nikolay Yashchenko
 */
public class ChatMessageDTOtoEntityConverter implements Converter<ChatMessageDTO, ChatMessage> {

    private ChatMessageService chatMessageService;
    private ChatDialogService chatDialogService;
    private UserService userService;

    public ChatMessageDTOtoEntityConverter(ChatMessageService chatMessageService,
                                           ChatDialogService chatDialogService,
                                           UserService userService) {
        this.chatMessageService = chatMessageService;
        this.chatDialogService = chatDialogService;
        this.userService = userService;
    }

    @Override
    public ChatMessage convert(ChatMessageDTO chatMessageDTO) {
        if (chatMessageDTO == null) return null;

        ChatMessage chatMessage = null;
        if (chatMessageDTO.getId() != null) {
            chatMessage = chatMessageService.read(chatMessageDTO.getId());
        }
        if (chatMessage == null) {
            chatMessage = new ChatMessage();
            chatMessage.setSendDate(Calendar.getInstance());
            chatMessage.setDialog(chatDialogService.read(chatMessageDTO.getDialogId()));
            chatMessage.setUserFrom(userService.read(chatMessageDTO.getUserFromId()));
        }

        chatMessage.setText(chatMessageDTO.getText());

        return chatMessage;
    }
}
