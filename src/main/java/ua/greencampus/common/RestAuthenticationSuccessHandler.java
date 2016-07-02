package ua.greencampus.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ivan Mikho on 05.04.16.
 */
@Component
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        clearAuthenticationAttributes(request);
        String redirectUrl = null;
        if (session != null) {
            redirectUrl = (String) session.getAttribute("url_redirect_login");
        }
        if (redirectUrl != null) {
            session.removeAttribute("url_prior_login");
            response.sendRedirect(redirectUrl);
        } else {
            response.sendRedirect("/");
        }

    }
}