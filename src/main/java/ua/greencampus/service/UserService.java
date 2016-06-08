package ua.greencampus.service;


import ua.greencampus.entity.User;

import java.util.List;


/**
 * Created by Arsenii on 25.03.2016.
 */
public interface UserService {
    User create(User user);
    User read(Long id);
    User readByEmail(String email);
    User update(User user);
    void delete(User user);
    User delete(Long id);
    Long getIdByEmail(String email);
    List<User> getByParams(int offset, int size, String sort);
}
