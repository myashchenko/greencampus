package ua.greencampus.service;

import ua.greencampus.entity.Role;

/**
 * @author Nikolay Yashchenko
 */
public interface AuthenticationService {
    Long getLoggedInUserId();
    Role getLoggedInUserRole();
}
