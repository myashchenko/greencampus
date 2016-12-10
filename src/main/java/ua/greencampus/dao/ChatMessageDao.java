package ua.greencampus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.greencampus.entity.ChatMessage;

import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public interface ChatMessageDao extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByDialogId(String dialogId);
}
