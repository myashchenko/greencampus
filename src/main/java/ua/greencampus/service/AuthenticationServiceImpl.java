package ua.greencampus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ua.greencampus.entity.Role;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Override
    public Long getLoggedInUserId() {
        User user = getUser();
        if (user != null) {
            return userService.getIdByEmail(user.getUsername());
        }
        return null;
    }

    @Override
    public Role getLoggedInUserRole() {
        return null;
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof User) {
                return (User) principal;
            }
        }
        return null;
    }
}
