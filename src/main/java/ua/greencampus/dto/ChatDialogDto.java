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
public class ChatDialogDto extends BaseDto {
    private String id;
    private String dialogName;
    private List<String> usersIds;
    private Map<String, Integer> unreadCount;
    private String avatarPath;

    public ChatDialogDto() {
        unreadCount = new HashMap<>();
    }

}
