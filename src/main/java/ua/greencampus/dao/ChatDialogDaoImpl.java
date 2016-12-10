package ua.greencampus.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.greencampus.entity.ChatDialog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class ChatDialogDaoImpl implements ChatDialogDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ChatDialog create(ChatDialog chatDialog) {
        entityManager.persist(chatDialog);
        return chatDialog;
    }

    @Override
    public ChatDialog read(String id) {
        return entityManager.find(ChatDialog.class, id);
    }

    @Override
    public ChatDialog update(ChatDialog chatDialog) {
        return entityManager.merge(chatDialog);
    }

    @Override
    public void delete(ChatDialog chatDialog) {
        entityManager.remove(chatDialog);
    }

    @Override
    public ChatDialog getByUserIds(String userToId, String userFromId) {
        String query = "select c1.chat_dialog_id from chat_dialog_users as c1 \n" +
                "join chat_dialog_users as c2 on \n" +
                "c2.user_id = :user_id2 AND \n" +
                "c1.user_id = :user_id1 AND \n" +
                "c1.chat_dialog_id = c2.chat_dialog_id";
        Object dialogId = entityManager.unwrap(Session.class)
                .createNativeQuery(query)
                .setParameter("user_id2", userToId)
                .setParameter("user_id1", userFromId)
                .uniqueResult();
        if (dialogId != null) {
            return read(dialogId.toString());
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ChatDialog> getByUserId(String userId) {
        return entityManager.unwrap(Session.class).createCriteria(ChatDialog.class)
                .createAlias("users", "u")
                .add(Restrictions.eq("u.id", userId))
                .addOrder(Order.desc("updatedDate"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public Integer getUnreadCount(String userId) {
        BigInteger result = (BigInteger) entityManager.unwrap(Session.class)
                .createSQLQuery("select sum(unread_count) from dialog_unread_messages where user_id = :user_id")
                .setParameter("user_id", userId)
                .uniqueResult();
        return Optional.ofNullable(result).map(BigInteger::intValue).orElse(0);
    }
}
