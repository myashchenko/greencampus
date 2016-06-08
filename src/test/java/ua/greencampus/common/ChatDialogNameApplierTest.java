package ua.greencampus.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.greencampus.config.AppSecurityConfig;
import ua.greencampus.config.ApplicationConfig;
import ua.greencampus.config.InMemoryDBConfig;
import ua.greencampus.entity.ChatDialog;
import ua.greencampus.entity.User;
import ua.greencampus.service.ChatDialogService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author Nikolay Yashchenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InMemoryDBConfig.class, ApplicationConfig.class, AppSecurityConfig.class})
public class ChatDialogNameApplierTest {

    @Autowired
    private ChatDialogService chatDialogService;

    @Test
    public void testWithoutName() {
        ChatDialog chatDialog = chatDialogService.read(2L);

        for (User user : chatDialog.getUsers()) {
            ChatDialog apply = chatDialog.prepareDialogName(user.getId());
            User otherUser = chatDialog.getUsers().stream()
                    .filter(u -> !u.getId().equals(user.getId()))
                    .findFirst()
                    .orElse(null);
            assertThat(apply.getDialogName(), equalTo(otherUser.getName()));
        }
    }

    @Test
    public void testWithName() {
        ChatDialog chatDialog = chatDialogService.read(1L);

        for (User user : chatDialog.getUsers()) {
            ChatDialog apply = chatDialog.prepareDialogName(user.getId());
            assertThat(apply.getDialogName(), equalTo("Test Dialog"));
        }
    }
}
