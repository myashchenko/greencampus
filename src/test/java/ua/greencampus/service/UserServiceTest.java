package ua.greencampus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.greencampus.config.AppSecurityConfig;
import ua.greencampus.config.ApplicationConfig;
import ua.greencampus.config.InMemoryDBConfig;
import ua.greencampus.entity.Role;
import ua.greencampus.entity.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author Nikolay Yashchenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InMemoryDBConfig.class, ApplicationConfig.class, AppSecurityConfig.class})
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRead() {
        User user = userService.read(1L);

        assertThat(user, notNullValue());
        assertThat(user.getId(), equalTo(1L));
        assertThat(user.getEmail(), equalTo("email1"));
        assertThat(user.getPassword(), equalTo("pass123"));
        assertThat(user.getRole(), equalTo(Role.ROLE_UNACTIVE));
    }
}
