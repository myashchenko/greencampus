package ua.greencampus.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import ua.greencampus.config.*;
import ua.greencampus.entity.ChatDialog;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author Nikolay Yashchenko
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {InMemoryDBConfig.class, ApplicationConfig.class, AppSecurityConfig.class})
public class ChatDialogServiceTest {

    @Autowired
    private ChatDialogService chatDialogService;

    @Test
    @Transactional
    public void testReadById() {
        ChatDialog chatDialog = chatDialogService.getByUserIds(1L);
        assertNotNull(chatDialog);
        assertThat(chatDialog.getId(), equalTo(1L));

        assertThat(chatDialog.getUsers(), hasSize(2));
    }

    @Test
    @Transactional
    public void testReadByUsersId() {
        ChatDialog chatDialog1 = chatDialogService.getByUserIds(1L, 2L);
        assertThat(chatDialog1, notNullValue());

        ChatDialog chatDialog2 = chatDialogService.getByUserIds(2L, 1L);
        assertThat(chatDialog2, notNullValue());

        assertThat(chatDialog1, equalTo(chatDialog2));

        assertThat(chatDialog1.getUsers(), hasSize(2));
    }
}
