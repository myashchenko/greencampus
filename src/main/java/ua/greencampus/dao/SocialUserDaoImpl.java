package ua.greencampus.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import ua.greencampus.entity.SocialUser;
import ua.greencampus.entity.User;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Yashchenko
 */
@Repository
public class SocialUserDaoImpl implements SocialUserDao {

    private static final String USER_ID = "user.id";//field of entity user
    private static final String PROVIDER_ID = "providerId";
    private static final String PROVIDER_USER_ID = "providerUserId";
    private static final String RANK = "rank";

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(SocialUser socialUser) {
        entityManager.persist(socialUser);
    }


    @Override
    @Transactional
    public SocialUser update(SocialUser socialUser) {
        return entityManager.merge(socialUser);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<SocialUser> findByUserId(Long userId) {
        return (List<SocialUser>) entityManager.unwrap(Session.class)
                .createCriteria(SocialUser.class)
                .add(Restrictions.eq(USER_ID, userId))
                .list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public SocialUser findByUserIdAndProviderId(Long userId, String providerId) {
        return (SocialUser) entityManager.unwrap(Session.class).createCriteria(SocialUser.class)
                .add(Restrictions.eq(USER_ID, userId))
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public SocialUser findByUserIdAndProviderUserIds(Long userId, MultiValueMap<String, String> providerUserIds) {
        Criteria criteria = entityManager.unwrap(Session.class).createCriteria(SocialUser.class);
        criteria.add(Restrictions.eq(USER_ID, userId));
        Disjunction or = Restrictions.disjunction();
        for (String providerId : providerUserIds.keySet()) {
            or.add(
                    Restrictions.and(
                            Restrictions.eq(PROVIDER_ID, providerId),
                            Restrictions.in(PROVIDER_USER_ID, providerUserIds.get(providerId))
                    )
            );
        }
        return (SocialUser) criteria.list();
    }

    @Override
    @Transactional(readOnly = true)
    public SocialUser get(Long userId, String providerId, String providerUserId) {
        return (SocialUser) entityManager.unwrap(Session.class).createCriteria(SocialUser.class)
                .add(Restrictions.eq(USER_ID, userId))
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .add(Restrictions.eq(PROVIDER_USER_ID, providerUserId))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public SocialUser findPrimaryByUserIdAndProviderId(Long userId, String providerId) {
        return (SocialUser) entityManager.unwrap(Session.class).createCriteria(SocialUser.class)
                .add(Restrictions.eq(USER_ID, userId))
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .addOrder(Order.asc(RANK))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Long> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId) {
        List<SocialUser> socialUsers = (List<SocialUser>) entityManager.unwrap(Session.class)
                .createCriteria(SocialUser.class)
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .add(Restrictions.eq(PROVIDER_USER_ID, providerUserId))
                .list();
        List<Long> userIds = new ArrayList<>(socialUsers.size());
        for (SocialUser socialUser : socialUsers) {
            userIds.add(socialUser.getUser().getId());
        }
        return userIds;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public List<Long> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds) {
        List<SocialUser> socialUsers = (List<SocialUser>) entityManager.unwrap(Session.class)
                .createCriteria(SocialUser.class)
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .add(Restrictions.in(PROVIDER_USER_ID, providerUserIds))
                .list();
        List<Long> userIds = new ArrayList<>(socialUsers.size());
        for (SocialUser socialUser : socialUsers) {
            userIds.add(socialUser.getUser().getId());
        }
        return userIds;
    }

    @Override
    @Transactional
    public void delete(SocialUser socialUser) {
        entityManager.remove(socialUser);
    }
}