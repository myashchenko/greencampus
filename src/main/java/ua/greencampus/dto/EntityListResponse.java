package ua.greencampus.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Nikolay Yashchenko
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityListResponse<T> extends BaseResponse {
    private List<T> entities;

    public EntityListResponse(List<T> entities) {
        this.entities = entities;
    }

    public List<T> getEntities() {
        return entities;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
    }
}
