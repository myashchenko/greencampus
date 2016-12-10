package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class ChatMessageDto extends BaseDto {
    private String id;
    private String userFromId;
    private String userFromName;
    private String text;
    private String date;
    private String dialogId;
    private String avatarPath;
}
