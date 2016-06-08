package ua.greencampus.entity;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

/**
 * @author Nikolay Yashchenko
 */
@Entity
@Table(name = "chat_dialog")
public class ChatDialog {

    @Id
    @SequenceGenerator(name = "chat_dialog_seq_gen", sequenceName = "chat_dialog_seq", allocationSize = 1)
    @GeneratedValue(generator = "chat_dialog_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "dialog_name")
    private String dialogName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "chat_dialog_users", joinColumns = @JoinColumn(name = "chat_dialog_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", nullable = false)
    private Calendar updateDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "dialog_unread_messages")
    @MapKeyColumn(name = "user_id")
    @Column(name = "unread_count")
    private Map<Long, Integer> unreadCount;

    @Transient
    private String avatarPath;

    public ChatDialog() {
        updateDate = Calendar.getInstance();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getDialogName() {
        return dialogName;
    }

    public void setDialogName(String dialogName) {
        this.dialogName = dialogName;
    }

    public Calendar getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Calendar updateDate) {
        this.updateDate = updateDate;
    }

    public Map<Long, Integer> getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(Map<Long, Integer> unreadCount) {
        this.unreadCount = unreadCount;
    }

    public void incrementUnreadCount(Long userId) {
        Integer count = unreadCount.get(userId);
        unreadCount.put(userId, count == null ? 1 : count + 1);
    }

    public void decrementUnreadCount(Long userId) {
        unreadCount.remove(userId);
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public ChatDialog prepareDialogName(Long userId) {
        if (getDialogName() == null) {
            String dialogName = getUsers().stream()
                    .filter(u -> !userId.equals(u.getId()))
                    .findFirst()
                    .map(User::getName)
                    .orElseThrow(IllegalArgumentException::new); // bad data in DB !!!!
            setDialogName(dialogName);
        }

        return this;
    }

    public ChatDialog prepareAvatarPath(Long userId) {
        if (getDialogName() == null) {
            String avatarPath = getUsers().stream()
                    .filter(u -> !userId.equals(u.getId()))
                    .findFirst()
                    .map(User::getAvatar)
                    .map(FileEntity::getPath)
                    .orElse(null);
            setAvatarPath(avatarPath);
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatDialog that = (ChatDialog) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
