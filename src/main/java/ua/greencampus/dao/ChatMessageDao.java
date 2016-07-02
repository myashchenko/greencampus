package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.ChatMessage;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatMessageDao extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByDialogId(Long dialogId);
}
