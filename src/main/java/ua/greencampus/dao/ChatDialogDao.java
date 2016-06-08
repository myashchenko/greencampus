package ua.greencampus.dao;

import ua.greencampus.entity.ChatDialog;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatDialogDao {
    ChatDialog create(ChatDialog chatDialog);
    ChatDialog read(Long id);
    ChatDialog update(ChatDialog chatDialog);
    void delete(ChatDialog chatDialog);
    ChatDialog getByUserIds(Long userToId, Long userFromId);
    List<ChatDialog> getByUserId(Long userId);
    Integer getUnreadCount(Long userId);
}
