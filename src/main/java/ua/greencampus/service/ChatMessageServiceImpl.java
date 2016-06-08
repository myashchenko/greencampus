package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.ChatMessageDao;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.ChatMessage;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageDao chatMessageDao;

    @Transactional
    @Override
    public ChatMessage create(ChatMessage chatMessage) {
        return chatMessageDao.create(chatMessage);
    }

    @Transactional(readOnly = true)
    @Override
    public ChatMessage read(Long id) {
        return chatMessageDao.read(id);
    }

    @Transactional
    @Override
    public ChatMessage update(ChatMessage chatMessage) {
        return chatMessageDao.update(chatMessage);
    }

    @Transactional
    @Override
    public void delete(ChatMessage chatMessage) {
        chatMessageDao.delete(chatMessage);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatMessage> getByDialog(ChatDialog chatDialog) {
        return chatMessageDao.getByDialog(chatDialog);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatMessage> getByDialogId(Long dialogId) {
        return chatMessageDao.getByDialogId(dialogId);
    }
}
