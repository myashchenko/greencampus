package ua.greencampus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ua.greencampus.filter.CorsFilter;

import javax.servlet.Filter;

/**
 * Created by Arsenii on 21.03.2016.
 */
@Configuration("webAppInitializer")
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ JpaConfig.class, WebMvcConfig.class, AppSecurityConfig.class, ApplicationConfig.class,
                WebSocketConfig.class, CloudinaryConfig.class, SocialConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebMvcConfig.class, AppSecurityConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{ new DelegatingFilterProxy("springSecurityFilterChain"), new CorsFilter() };
    }
}
