package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.dao.UserDAO;
import ua.greencampus.entity.User;

import java.util.List;

/**
 * Created by Arsenii on 25.03.2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.create(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User read(Long id) {
        return userDAO.read(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User readByEmail(String email) {
        return userDAO.readByEmail(email);
    }

    @Transactional
    @Override
    public User update(User user) {
        return userDAO.update(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getIdByEmail(String email) {
        return userDAO.getIdByEmail(email);
    }

    @Transactional
    @Override
    public User delete(Long id) {
        User user = userDAO.read(id);
        delete(user);
        return user;
    }

    @Transactional
    @Override
    public List<User> getByParams(int offset, int size, String sort) {
        return userDAO.getByParams(offset, size, sort);
    }
}