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

    List<SocialUser> findByUserId(Long userId);

    SocialUser findByUserIdAndProviderId(Long userId, String providerId);

    SocialUser findByUserIdAndProviderUserIds(Long userId, MultiValueMap<String, String> providerUserIds);

    SocialUser get(Long userId, String providerId, String providerUserId);

    SocialUser findPrimaryByUserIdAndProviderId(Long userId, String providerId);

    List<Long> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);

    List<Long> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds);

    void delete(SocialUser socialUser);
}
