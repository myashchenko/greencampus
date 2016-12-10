package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykola Yashchenko
 */
@Getter
@Setter
public abstract class BaseDto {
    private String createdBy;
    private String createdDate;
    private String updatedBy;
    private String updatedDate;
}
