package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.UserDao;
import ua.greencampus.entity.Role;
import ua.greencampus.entity.User;

import java.util.List;

/**
 * Created by Arsenii on 25.03.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User read(String id) {
        return userDao.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User readByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Transactional
    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public String getIdByEmail(String email) {
        List<Object> idByEmail = userDao.findIdByEmail(email);
        if (idByEmail.isEmpty()) {
            return null;
        }
        return (String) idByEmail.iterator().next();
    }

    @Override
    public Role getRoleByEmail(String email) {
        List<Object> idByEmail = userDao.findRoleByEmail(email);
        if (idByEmail.isEmpty()) {
            return null;
        }
        return (Role) idByEmail.iterator().next();
    }

    @Transactional
    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Transactional
    @Override
    public List<User> getByParams(int page, int size, String sort) {
        // todo sort
        return userDao.findAll(new PageRequest(page, size)).getContent();
    }

    @Transactional
    @Override
    public void updatePassword(String userId, String newPassword) {
        userDao.updatePassword(userId, newPassword);
    }
}