package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.ChatDialogDao;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.User;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class ChatDialogServiceImpl implements ChatDialogService {

    @Autowired
    private ChatDialogDao chatDialogDao;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public ChatDialog create(ChatDialog chatDialog) {
        return chatDialogDao.create(chatDialog);
    }

    @Transactional
    @Override
    public ChatDialog createOrGet(Long userId) {
        Long loggedInUserId = authenticationService.getLoggedInUserId();
        Optional.ofNullable(loggedInUserId).orElseThrow(IllegalStateException::new);

        ChatDialog chatDialog = getByUserIds(userId, loggedInUserId);

        if (chatDialog == null) {
            User userTo = userService.read(userId);
            Optional.ofNullable(userTo).orElseThrow(IllegalStateException::new);
            User currentUser = userService.read(loggedInUserId);
            chatDialog = new ChatDialog();
            chatDialog.setUsers(new LinkedHashSet<>(Arrays.asList(userTo, currentUser)));
            chatDialog = create(chatDialog);
        }

        return chatDialog;
    }

    @Transactional(readOnly = true)
    @Override
    public ChatDialog read(Long id) {
        return chatDialogDao.read(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ChatDialog getByUserIds(Long id) {
        return chatDialogDao.read(id);
    }

    @Transactional
    @Override
    public ChatDialog update(ChatDialog chatDialog) {
        return chatDialogDao.update(chatDialog);
    }

    @Transactional
    @Override
    public void delete(ChatDialog chatDialog) {
        chatDialogDao.delete(chatDialog);
    }

    @Transactional(readOnly = true)
    @Override
    public ChatDialog getByUserIds(Long userToId, Long userFromId) {
        return chatDialogDao.getByUserIds(userToId, userFromId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatDialog> getByUserId(Long userId) {
        return chatDialogDao.getByUserId(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public Integer getUnreadCount(Long userId) {
        return chatDialogDao.getUnreadCount(userId);
    }
}
