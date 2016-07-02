package ua.greencampus.dao;

import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.ChatMessage;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatMessageDao {
    ChatMessage create(ChatMessage chatMessage);
    ChatMessage read(Long id);
    ChatMessage update(ChatMessage chatMessage);
    void delete(ChatMessage chatMessage);
    List<ChatMessage> getByDialogId(Long dialogId);
}
