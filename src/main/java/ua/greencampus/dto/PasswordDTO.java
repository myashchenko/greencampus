package ua.greencampus.dto;

/**
 * Created by Arsenii on 22.05.2016.
 */
public class PasswordDTO {
    private Long id;
    private String oldPassword;
    private String newPassword;

    public PasswordDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
