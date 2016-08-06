package ua.greencampus.dao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.social.connect.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ua.greencampus.entity.SocialUser;
import ua.greencampus.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
public class ConnectionRepositoryImpl implements ConnectionRepository {

    private User user;
    private SocialUserDao socialUserService;
    private ConnectionFactoryLocator connectionFactoryLocator;

    public ConnectionRepositoryImpl(User user, SocialUserDao socialUserService,
                                    ConnectionFactoryLocator connectionFactoryLocator) {
        this.user = user;
        this.socialUserService = socialUserService;
        this.connectionFactoryLocator = connectionFactoryLocator;
    }

    @Override
    @Transactional(readOnly = true)
    public MultiValueMap<String, Connection<?>> findAllConnections() {
        MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<>();

        List<SocialUser> allSocialUsers = socialUserService.findByUserId(user.getId());
        for (SocialUser socialUser : allSocialUsers) {
            ConnectionData connectionData = toConnectionData(socialUser);
            Connection<?> connection = createConnection(connectionData);
            connections.add(connectionData.getProviderId(), connection);
        }

        return connections;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Connection<?>> findConnections(String providerId) {
        List<Connection<?>> connections = new ArrayList<>();

        SocialUser socialUser = socialUserService.findByUserIdAndProviderId(user.getId(), providerId);
        if (socialUser != null) {
            ConnectionData connectionData = toConnectionData(socialUser);
            Connection<?> connection = createConnection(connectionData);
            connections.add(connection);
        }

        return connections;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();

        List<?> connections = findConnections(providerId);
        return (List<Connection<A>>) connections;
    }

    @Override
    @Transactional(readOnly = true)
    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
        MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<>();

        SocialUser socialUser = socialUserService.findByUserIdAndProviderUserIds(user.getId(), providerUserIds);
        ConnectionData connectionData = toConnectionData(socialUser);
        Connection<?> connection = createConnection(connectionData);
        connections.add(connectionData.getProviderId(), connection);

        return connections;
    }

    @Override
    @Transactional(readOnly = true)
    public Connection<?> getConnection(ConnectionKey connectionKey) {
        SocialUser socialUser = socialUserService.get(user.getId(), connectionKey.getProviderId(),
                connectionKey.getProviderUserId());
        if (socialUser == null) {
            throw new NoSuchConnectionException(connectionKey);
        }
        return createConnection(toConnectionData(socialUser));
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
        SocialUser socialUser = socialUserService.get(user.getId(), providerId, providerUserId);
        if (socialUser == null) {
            throw new NoSuchConnectionException(new ConnectionKey(providerId, providerUserId));
        }
        return (Connection<A>) createConnection(toConnectionData(socialUser));
    }

    @Override
    @Transactional(readOnly = true)
    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        Connection<A> connection = findPrimaryConnection(apiType);
        if (connection == null) {
            String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
            throw new NotConnectedException(providerId);
        }
        return connection;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly = true)
    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();

        SocialUser socialUser = socialUserService.findPrimaryByUserIdAndProviderId(user.getId(), providerId);
        Connection<A> connection = null;
        if (socialUser != null) {
            connection = (Connection<A>) createConnection(toConnectionData(socialUser));
        }

        return connection;
    }

    @Transactional
    @Override
    public void addConnection(Connection<?> connection) {
        ConnectionData connectionData = connection.createData();

        List<Long> userIds = socialUserService.findUserIdsByProviderIdAndProviderUserId(connectionData.getProviderId(),
                connectionData.getProviderUserId());
        if (!userIds.isEmpty()) {
            throw new DuplicateConnectionException(new ConnectionKey(connectionData.getProviderId(),
                    connectionData.getProviderUserId()));
        }

        SocialUser socialUser = socialUserService.findByUserIdAndProviderId(user.getId(),
                connectionData.getProviderId());
        if (socialUser != null) {
            throw new DuplicateConnectionException(new ConnectionKey(connectionData.getProviderId(),
                    connectionData.getProviderUserId()));
        }

        SocialUser newSocialUser = new SocialUser();
        newSocialUser.setUser(user);
        newSocialUser.setProviderId(connectionData.getProviderId());
        newSocialUser.setProviderUserId(connectionData.getProviderUserId());
        newSocialUser.setDisplayName(connectionData.getDisplayName());
        newSocialUser.setProfileUrl(connectionData.getProfileUrl());
        newSocialUser.setImageUrl(connectionData.getImageUrl());

        newSocialUser.setAccessToken(encrypt(connectionData.getAccessToken()));
        newSocialUser.setSecret(encrypt(connectionData.getSecret()));
        newSocialUser.setRefreshToken(encrypt(connectionData.getRefreshToken()));

        newSocialUser.setExpireTime(connectionData.getExpireTime());

        try {
            socialUserService.create(newSocialUser);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateConnectionException(new ConnectionKey(connectionData.getProviderId(),
                    connectionData.getProviderUserId()));
        }
    }

    @Transactional
    @Override
    public void updateConnection(Connection<?> connection) {
        ConnectionData connectionData = connection.createData();
        SocialUser socialUser = socialUserService.get(user.getId(), connectionData.getProviderId(),
                connectionData.getProviderUserId());
        if (socialUser != null) {
            socialUser.setDisplayName(connectionData.getDisplayName());
            socialUser.setProfileUrl(connectionData.getProfileUrl());
            socialUser.setImageUrl(connectionData.getImageUrl());

            socialUser.setAccessToken(encrypt(connectionData.getAccessToken()));
            socialUser.setSecret(encrypt(connectionData.getSecret()));
            socialUser.setRefreshToken(encrypt(connectionData.getRefreshToken()));

            socialUser.setExpireTime(connectionData.getExpireTime());
            socialUserService.update(socialUser);
        }
    }

    @Transactional
    @Override
    public void removeConnections(String providerId) {
        SocialUser socialUser = socialUserService.findByUserIdAndProviderId(user.getId(), providerId);
        socialUserService.delete(socialUser);
    }

    @Transactional
    @Override
    public void removeConnection(ConnectionKey connectionKey) {
        SocialUser socialUser = socialUserService.get(user.getId(), connectionKey.getProviderId(),
                connectionKey.getProviderUserId());
        if (socialUser != null) {
            socialUserService.delete(socialUser);
        }
    }

    private ConnectionData toConnectionData(SocialUser socialUser) {
        return new ConnectionData(socialUser.getProviderId(),
                socialUser.getProviderUserId(),
                socialUser.getDisplayName(),
                socialUser.getProfileUrl(),
                socialUser.getImageUrl(),

                decrypt(socialUser.getAccessToken()),
                decrypt(socialUser.getSecret()),
                decrypt(socialUser.getRefreshToken()),

                convertZeroToNull(socialUser.getExpireTime()));
    }

    private Connection<?> createConnection(ConnectionData connectionData) {
        ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(
                connectionData.getProviderId());
        return connectionFactory.createConnection(connectionData);
    }

    private Long convertZeroToNull(Long expireTime) {
        return expireTime != null && expireTime == 0 ? null : expireTime;
    }

    private String decrypt(String encryptedText) {
        return encryptedText;
    }

    private String encrypt(String text) {
        return text;
    }
}
