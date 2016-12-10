package ua.greencampus.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ua.greencampus.entity.Role;

/**
 * @author Nikolay Yashchenko
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static ThreadLocal<String> currentUserId = new ThreadLocal<>();
    private static ThreadLocal<Role> currentUserRole = new ThreadLocal<>();

    private UserService userService;

    @Override
    public String getLoggedInUserId() {
        String id = currentUserId.get();
        if (id == null) {
            User user = getUser();
            if (user != null) {
                id = userService.getIdByEmail(user.getUsername());
                currentUserId.set(id);
            }
        }

        return id;
    }

    @Override
    public Role getLoggedInUserRole() {
        Role role = currentUserRole.get();
        if (role == null) {
            User user = getUser();
            if (user != null) {
                role = userService.getRoleByEmail(user.getUsername());
                currentUserRole.set(role);
            }
        }
        return role;
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
