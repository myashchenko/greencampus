package ua.greencampus.service;

import ua.greencampus.entity.ChatDialog;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatDialogService {
    ChatDialog create(ChatDialog chatDialog);
    ChatDialog createOrGet(String userId);
    ChatDialog read(String id);
    ChatDialog getByUserIds(String id);
    ChatDialog update(ChatDialog chatDialog);
    void delete(ChatDialog chatDialog);
    ChatDialog getByUserIds(String userToId, String userFromId);
    List<ChatDialog> getByUserId(String userId);
    Integer getUnreadCount(String userId);
}
