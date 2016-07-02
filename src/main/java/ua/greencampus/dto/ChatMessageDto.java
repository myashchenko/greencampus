package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class ChatMessageDto {
    private Long id;
    private Long userFromId;
    private String userFromName;
    private String text;
    private String date;
    private Long dialogId;
    private String avatarPath;
}
