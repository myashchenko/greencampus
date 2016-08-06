package ua.greencampus.dao;

import org.springframework.social.connect.*;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.service.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Nikolay Yashchenko
 */
public class SocialUserConnectionRepository implements UsersConnectionRepository {

    private SocialUserDao socialUserDao;
    private ConnectionFactoryLocator connectionFactoryLocator;
    private UserService userService;


    public SocialUserConnectionRepository(SocialUserDao socialUserDao,
                                          ConnectionFactoryLocator connectionFactoryLocator, UserService userService) {
        this.socialUserDao = socialUserDao;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.userService = userService;
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        ConnectionKey key = connection.getKey();

        return socialUserDao.findUserIdsByProviderIdAndProviderUserId(key.getProviderId(), key.getProviderUserId())
                .stream()
                .map(Object::toString)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        return socialUserDao.findUserIdsByProviderIdAndProviderUserIds(providerId, providerUserIds)
                .stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        Long longUserId = Long.parseLong(userId);
        return new ConnectionRepositoryImpl(
                userService.read(longUserId),
                socialUserDao,
                connectionFactoryLocator
        );
    }
}
