package ua.greencampus.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "chat_dialog")
@Getter
@Setter
public class ChatDialog extends BaseEntity {

    @Column(name = "dialog_name")
    private String dialogName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "chat_dialog_users", joinColumns = @JoinColumn(name = "chat_dialog_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dialog_unread_messages")
    @MapKeyColumn(name = "user_id")
    @Column(name = "unread_count")
    private Map<Long, Integer> unreadCount;

    @Transient
    private String avatarPath;

    public void incrementUnreadCount(Long userId) {
        Integer count = unreadCount.get(userId);
        unreadCount.put(userId, count == null ? 1 : count + 1);
    }

    public void decrementUnreadCount(Long userId) {
        unreadCount.remove(userId);
    }

    public ChatDialog prepareDialogName(Long userId) {
        if (getDialogName() == null) {
            String newDialogName = getUsers().stream()
                    .filter(u -> !userId.equals(u.getId()))
                    .findFirst()
                    .map(User::getName)
                    .orElseThrow(IllegalArgumentException::new); // bad data in DB !!!!
            setDialogName(newDialogName);
        }

        return this;
    }

    public ChatDialog prepareAvatarPath(Long userId) {
        if (getDialogName() == null) {
            String newAvatarPath = getUsers().stream()
                    .filter(u -> !userId.equals(u.getId()))
                    .findFirst()
                    .map(User::getAvatar)
                    .map(FileEntity::getPath)
                    .orElse(null);
            setAvatarPath(newAvatarPath);
        }
        return this;
    }
}
