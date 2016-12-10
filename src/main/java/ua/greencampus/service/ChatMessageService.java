package ua.greencampus.service;

import ua.greencampus.entity.ChatMessage;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatMessageService {
    ChatMessage create(ChatMessage chatMessage);

    ChatMessage read(String id);

    ChatMessage update(ChatMessage chatMessage);

    void delete(ChatMessage chatMessage);

    List<ChatMessage> getByDialogId(String dialogId);
}
