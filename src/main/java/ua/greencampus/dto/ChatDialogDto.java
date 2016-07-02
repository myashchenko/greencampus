package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class ChatDialogDto {
    private Long id;
    private String dialogName;
    private List<Long> usersIds;
    private Map<Long, Integer> unreadCount;
    private String avatarPath;

    public ChatDialogDto() {
        unreadCount = new HashMap<>();
    }

}
