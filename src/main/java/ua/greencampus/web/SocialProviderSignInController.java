package ua.greencampus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ua.greencampus.common.SimpleSignInAdapter;
import ua.greencampus.service.UserService;

/**
 * @author Nikolay Yashchenko
 */
@Controller
public class SocialProviderSignInController extends ProviderSignInController {

    @Autowired
    public SocialProviderSignInController(ConnectionFactoryLocator connectionFactoryLocator,
                                          UsersConnectionRepository usersConnectionRepository,
                                          UserDetailsService userDetailsService, UserService userService) {
        super(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(userDetailsService,
                userService));
    }

    @GetMapping(value = "/helper/closeWindow")
    public String closeWindow() {
        return "helper/closeWindow";
    }
}
