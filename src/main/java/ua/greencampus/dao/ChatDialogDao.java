package ua.greencampus.dao;

import ua.greencampus.entity.ChatDialog;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatDialogDao {
    ChatDialog create(ChatDialog chatDialog);
    ChatDialog read(String id);
    ChatDialog update(ChatDialog chatDialog);
    void delete(ChatDialog chatDialog);
    ChatDialog getByUserIds(String userToId, String userFromId);
    List<ChatDialog> getByUserId(String userId);
    Integer getUnreadCount(String userId);
}
