package ua.greencampus.service;

import ua.greencampus.entity.ChatDialog;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatDialogService {
    ChatDialog create(ChatDialog chatDialog);
    ChatDialog createOrGet(Long userId);
    ChatDialog read(Long id);
    ChatDialog getByUserIds(Long id);
    ChatDialog update(ChatDialog chatDialog);
    void delete(ChatDialog chatDialog);
    ChatDialog getByUserIds(Long userToId, Long userFromId);
    List<ChatDialog> getByUserId(Long userId);
    Integer getUnreadCount(Long userId);
}
