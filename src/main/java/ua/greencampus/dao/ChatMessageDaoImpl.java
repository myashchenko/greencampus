package ua.greencampus.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.ChatMessage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class ChatMessageDaoImpl implements ChatMessageDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ChatMessage create(ChatMessage chatMessage) {
        entityManager.persist(chatMessage);
        return chatMessage;
    }

    @Override
    public ChatMessage read(Long id) {
        return entityManager.find(ChatMessage.class, id);
    }

    @Override
    public ChatMessage update(ChatMessage chatMessage) {
        return entityManager.merge(chatMessage);
    }

    @Override
    public void delete(ChatMessage chatMessage) {
        entityManager.remove(chatMessage);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ChatMessage> getByDialogId(Long dialogId) {
        return entityManager.unwrap(Session.class).createCriteria(ChatMessage.class)
                .createAlias("dialog", "d")
                .add(Restrictions.eq("d.id", dialogId))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .addOrder(Order.asc("sendDate"))
                .list();
    }
}
