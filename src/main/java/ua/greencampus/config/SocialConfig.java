package ua.greencampus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ReconnectFilter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.facebook.web.DisconnectController;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteConnectionFactory;
import ua.greencampus.dao.SocialUserConnectionRepository;
import ua.greencampus.dao.SocialUserDao;
import ua.greencampus.service.AuthenticationService;
import ua.greencampus.service.UserService;

/**
 * @author Nikolay Yashchenko
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private SocialUserDao socialUserDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @Value("${google.consumerKey}")
    private String GOOGLE_CONSUMER_KEY;
    @Value("${google.consumerSecret}")
    private String GOOGLE_CONSUMER_SECRET;
    @Value("${vkontakte.consumerKey}")
    private String VKONTAKTE_CONSUMER_KEY;
    @Value("${vkontakte.consumerSecret}")
    private String VKONTAKTE_CONSUMER_SECRET;
    @Value("${facebook.consumerKey}")
    private String FACEBOOK_CONSUMER_KEY;
    @Value("${facebook.consumerSecret}")
    private String FACEBOOK_CONSUMER_SECRET;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new GoogleConnectionFactory(GOOGLE_CONSUMER_KEY, GOOGLE_CONSUMER_SECRET));
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(FACEBOOK_CONSUMER_KEY, FACEBOOK_CONSUMER_SECRET));
        cfConfig.addConnectionFactory(new VKontakteConnectionFactory(VKONTAKTE_CONSUMER_KEY, VKONTAKTE_CONSUMER_SECRET));
    }

    @Override
    public UserIdSource getUserIdSource() {
        return () -> {
            Long loggedinUserId = authenticationService.getLoggedInUserId();
            if (loggedinUserId == null) {
                throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
            }
            return loggedinUserId.toString();
        };
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new SocialUserConnectionRepository(socialUserDao, connectionFactoryLocator, userService);
    }

    @Bean
    public DisconnectController disconnectController(UsersConnectionRepository usersConnectionRepository) {
        return new DisconnectController(usersConnectionRepository, FACEBOOK_CONSUMER_SECRET);
    }

    @Bean
    public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository,
                                               UserIdSource userIdSource) {
        return new ReconnectFilter(usersConnectionRepository, userIdSource);
    }

    @Bean(name = "connectionRepository")
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository(
            UsersConnectionRepository socialUserService,
            AuthenticationService authenticationService) {
        return socialUserService.createConnectionRepository(
                authenticationService.getLoggedInUserId().toString()
        );
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Facebook facebook(ConnectionRepository repository) {
        Connection<Facebook> connection = repository.findPrimaryConnection(Facebook.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public Google linkedin(ConnectionRepository repository) {
        Connection<Google> connection = repository.findPrimaryConnection(Google.class);
        return connection != null ? connection.getApi() : null;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
    public VKontakte vKontakte(ConnectionRepository repository) {
        Connection<VKontakte> connection = repository.findPrimaryConnection(VKontakte.class);
        return connection != null ? connection.getApi() : null;
    }
}
