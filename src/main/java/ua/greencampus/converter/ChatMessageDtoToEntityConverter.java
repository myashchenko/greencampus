package ua.greencampus.converter;

import org.springframework.core.convert.converter.Converter;
import ua.greencampus.dto.ChatMessageDto;
import ua.greencampus.entity.ChatMessage;
import ua.greencampus.service.ChatDialogService;
import ua.greencampus.service.ChatMessageService;
import ua.greencampus.service.UserService;

import java.util.Calendar;

/**
 * @author Nikolay Yashchenko
 */
public class ChatMessageDtoToEntityConverter implements Converter<ChatMessageDto, ChatMessage> {

    private ChatMessageService chatMessageService;
    private ChatDialogService chatDialogService;
    private UserService userService;

    public ChatMessageDtoToEntityConverter(ChatMessageService chatMessageService,
                                           ChatDialogService chatDialogService,
                                           UserService userService) {
        this.chatMessageService = chatMessageService;
        this.chatDialogService = chatDialogService;
        this.userService = userService;
    }

    @Override
    public ChatMessage convert(ChatMessageDto chatMessageDto) {
        if (chatMessageDto == null) {
            return null;
        }

        ChatMessage chatMessage = null;
        if (chatMessageDto.getId() != null) {
            chatMessage = chatMessageService.read(chatMessageDto.getId());
        }
        if (chatMessage == null) {
            chatMessage = new ChatMessage();
            chatMessage.setSendDate(Calendar.getInstance());
            chatMessage.setDialog(chatDialogService.read(chatMessageDto.getDialogId()));
            chatMessage.setUserFrom(userService.read(chatMessageDto.getUserFromId()));
        }

        chatMessage.setText(chatMessageDto.getText());

        return chatMessage;
    }
}
