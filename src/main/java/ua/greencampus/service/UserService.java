package ua.greencampus.service;

import ua.greencampus.entity.Role;
import ua.greencampus.entity.User;

import java.util.List;

/**
 * Created by Arsenii on 25.03.2016.
 */
public interface UserService {
    User create(User user);
    User read(String id);
    User readByEmail(String email);
    User update(User user);
    void delete(User user);
    void delete(String id);
    String getIdByEmail(String email);
    Role getRoleByEmail(String email);
    List<User> getByParams(int offset, int size, String sort);
    void updatePassword(String userId, String newPassword);
}
