package ua.greencampus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ua.greencampus.common.RestAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Arsenii on 25.03.2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers(
                "/resources/**", "/favicon.ico", "/api/**", "/auth", "/auth/**", "/ws/**", "/access-denied",
                "/signin/**").permitAll();
        http.authorizeRequests().antMatchers(
                "/chat/**", "/user/update", "/user/account", "/user/update/password").authenticated();
        http.authorizeRequests().antMatchers("/course/create").hasRole("TEACHER");
        http.authorizeRequests().antMatchers("/user/update/**", "/api/user/delete/**").hasRole("ADMIN");

        http.csrf().disable();

        http.exceptionHandling().authenticationEntryPoint((req, res, e) -> res.sendRedirect("/access-denied"))
                .accessDeniedPage("/access-denied")
                .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/access-denied"));
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessHandler((req, res, authentication) -> {
                    res.setStatus(HttpServletResponse.SC_OK);
                    res.sendRedirect("/");
                    res.getWriter().flush();
                }).permitAll();

        http.formLogin().loginPage("/login").permitAll().failureUrl("/login")
                .loginProcessingUrl("/auth").usernameParameter("email").passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler((req, res, authentication) -> res.sendRedirect("/login"));

        http.httpBasic().authenticationEntryPoint(getBasicAuthenticationEntryPoint());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BasicAuthenticationFilter getBasicAuthentication() throws Exception {
        return new BasicAuthenticationFilter(authenticationManagerBean(), getBasicAuthenticationEntryPoint());
    }

    @Bean
    public BasicAuthenticationEntryPoint getBasicAuthenticationEntryPoint(){
        BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new BasicAuthenticationEntryPoint();
        basicAuthenticationEntryPoint.setRealmName("GreenCampus Basic Authentication");
        return basicAuthenticationEntryPoint;
    }
}