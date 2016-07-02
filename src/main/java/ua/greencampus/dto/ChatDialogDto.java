package ua.greencampus.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nikolay Yashchenko
 */
public class ChatDialogDto {
    private Long id;
    private String dialogName;
    private List<Long> usersIds;
    private Map<Long, Integer> unreadCount;
    private String avatarPath;

    public ChatDialogDto() {
        unreadCount = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDialogName() {
        return dialogName;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public List<Long> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(List<Long> usersIds) {
        this.usersIds = usersIds;
    }

    public Map<Long, Integer> getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Map<Long, Integer> unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
