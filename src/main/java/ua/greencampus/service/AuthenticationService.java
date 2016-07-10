package ua.greencampus.service;

/**
 * @author Nikolay Yashchenko
 */
@FunctionalInterface
public interface AuthenticationService {
    Long getLoggedInUserId();
}
