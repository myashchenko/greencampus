package ua.greencampus.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;
import ua.greencampus.entity.FileEntity;
import ua.greencampus.entity.Role;
import ua.greencampus.entity.User;
import ua.greencampus.service.UserService;

/**
 * @author Nikolay Yashchenko
 */
@Service
public class ProviderSignInUtils extends org.springframework.social.connect.web.ProviderSignInUtils {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public ProviderSignInUtils(ConnectionFactoryLocator connectionFactoryLocator,
                               UsersConnectionRepository socialUserService) {
        super(connectionFactoryLocator, socialUserService);
    }

    public void doPostSignUp(String email, ServletWebRequest request) {
        String userId = null;
        Connection<?> connection = getConnectionFromSession(request);
        if (connection != null && email != null && !email.isEmpty()) {
            SocialConnectionAdapter connectionAdapter = new SocialConnectionAdapter(connection);
            User user;
            if ((user = userService.readByEmail(email)) == null) {
                String avatarUrl = connectionAdapter.getAvatarUrl();
                FileEntity avatar = null;
                if (avatarUrl != null) {
                    avatar = new FileEntity();
                    avatar.setPath(avatarUrl);
                }
                user = new User(email, email, connection.getDisplayName(), avatar, Role.ROLE_UNACTIVE);
                user = userService.create(user);
            }
            userId = user.getId().toString();
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                    userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        super.doPostSignUp(userId, request);
    }
}
