package ua.greencampus.dao;

import ua.greencampus.entity.User;

import java.util.List;

/**
 * Created by Arsenii on 21.03.2016.
 */
public interface UserDAO {
    User create(User user);
    User read(Long id);
    User readByEmail(String email);
    User update(User user);
    void delete(User user);
    Long getIdByEmail(String email);
    List<User> getByParams(int offset, int size, String sort);
}
