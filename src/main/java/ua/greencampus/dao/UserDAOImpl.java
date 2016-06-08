package ua.greencampus.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ua.greencampus.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Arsenii on 21.03.2016.
 */
@Repository
public class UserDAOImpl implements  UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User create(User user){
        entityManager.persist(user);
        return user;
    }

    @Override
    public User read(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User readByEmail(String email){
        return (User) ((Session) entityManager.getDelegate())
                .createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public User update(User user){
        return entityManager.merge(user);
    }

    @Override
    public void delete(User user){
        entityManager.remove(user);
    }

    @Override
    public Long getIdByEmail(String email) {
        return (Long) entityManager.unwrap(Session.class).createCriteria(User.class)
                .add(Restrictions.eq("email", email))
                .setProjection(Projections.property("id"))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getByParams(int offset, int size, String sort) {
        return ((Session) entityManager.getDelegate()).createCriteria(User.class)
                .setFirstResult(offset * size)
                .setMaxResults(size)
                .list();
    }
}
