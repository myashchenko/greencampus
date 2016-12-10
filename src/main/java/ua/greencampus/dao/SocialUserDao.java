package ua.greencampus.dao;

import org.springframework.util.MultiValueMap;
import ua.greencampus.entity.SocialUser;

import java.util.List;
import java.util.Set;

/**
 * @author Nikolay Yashchenko
 */
public interface SocialUserDao {
    void create(SocialUser socialUser);

    SocialUser update(SocialUser socialUser);

    List<SocialUser> findByUserId(String userId);

    SocialUser findByUserIdAndProviderId(String userId, String providerId);

    SocialUser findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds);

    SocialUser get(String userId, String providerId, String providerUserId);

    SocialUser findPrimaryByUserIdAndProviderId(String userId, String providerId);

    List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);

    List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds);

    void delete(SocialUser socialUser);
}
