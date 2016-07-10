package ua.greencampus.common;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import ua.greencampus.service.AuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Nikolay Yashchenko
 */
public class UserInfoInterceptor extends HandlerInterceptorAdapter {

    private AuthenticationService authenticationService;

    public UserInfoInterceptor(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.addObject("loggedInUserId", authenticationService.getLoggedInUserId());
        }
    }
}
