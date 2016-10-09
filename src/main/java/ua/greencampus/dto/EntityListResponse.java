package ua.greencampus.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@Getter
@Setter
public class EntityListResponse<T> extends BaseResponse {
    private List<T> entities;

    public EntityListResponse(List<T> entities) {
        this.entities = entities;
    }
}
