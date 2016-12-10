package ua.greencampus.service;

import ua.greencampus.entity.Role;

/**
 * @author Nikolay Yashchenko
 */
public interface AuthenticationService {
    String getLoggedInUserId();
    Role getLoggedInUserRole();
}
