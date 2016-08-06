package ua.greencampus.common;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.google.api.Google;
import org.springframework.social.vkontakte.api.VKontakte;
import org.springframework.social.vkontakte.connect.VKontakteAdapter;

import java.util.function.Function;

/**
 * @author Nikolay Yashchenko
 */
public class SocialConnectionAdapter {

    private final Connection<?> connection;

    public SocialConnectionAdapter(Connection<?> connection) {
        this.connection = connection;
    }

    public String getEmail() {
        return executeActionForSocial(
                googleApi -> googleApi.plusOperations().getGoogleProfile().getAccountEmail(),
                facebookApi -> facebookApi.userOperations().getUserProfile().getEmail(),
                vkontakteApi -> {
                    try {
                        VKontakteAdapter vKontakteAdapter = new VKontakteAdapter();
                        return vKontakteAdapter.fetchUserProfile(vkontakteApi).getEmail();
                    } catch (Exception e) {
                        return "";
                    }
                }
        );
    }

    public String getAvatarUrl() {
        return executeActionForSocial(
                googleApi -> {
                    String imgUrl = googleApi.plusOperations().getGoogleProfile().getImageUrl();
                    imgUrl = imgUrl.split("\\?")[0];
                    return imgUrl;
                },
                facebookApi -> connection.getImageUrl() + "/?type=large",
                vKontakteApi -> vKontakteApi.usersOperations().getUser().getPhotoMax()
        );
    }

    private <R> R executeActionForSocial(Function<Google, R> googleFunction, Function<Facebook, R> facebookFunction,
                                         Function<VKontakte, R> vkontakteFunction) {
        Object api = connection.getApi();
        R result;
        if (api instanceof Google) {
            result = googleFunction.apply((Google) api);
        } else if (api instanceof Facebook) {
            result = facebookFunction.apply((Facebook) api);
        } else if (api instanceof VKontakte) {
            result = vkontakteFunction.apply((VKontakte) api);
        } else {
            throw new RuntimeException("Unknown social api");
        }
        return result;
    }
}
