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
        return chatMessageDao.save(chatMessage);
    }

    @Transactional(readOnly = true)
    @Override
    public ChatMessage read(Long id) {
        return chatMessageDao.findOne(id);
    }

    @Transactional
    @Override
    public ChatMessage update(ChatMessage chatMessage) {
        return chatMessageDao.save(chatMessage);
    }

    @Transactional
    @Override
    public void delete(ChatMessage chatMessage) {
        chatMessageDao.delete(chatMessage);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ChatMessage> getByDialogId(Long dialogId) {
        return chatMessageDao.findByDialogId(dialogId);
    }
}
