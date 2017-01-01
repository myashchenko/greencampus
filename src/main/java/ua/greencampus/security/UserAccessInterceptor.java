package ua.greencampus.security;

import org.springframework.security.core.context.SecurityContextHolder;
import ua.greencampus.entity.User;

import java.util.Objects;

/**
 * @author Mykola Yashchenko
 */
public class UserAccessInterceptor implements EntityAccessInterceptor<User> {
    @Override
    public boolean check(User user) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        String entityAuthor = user.getEmail();
        return Objects.equals(currentUser, entityAuthor);
    }

    @Override
    public Class<User> getSupportedClass() {
        return User.class;
    }
}
