package ua.greencampus.dto;

/**
 * @author Nikolay Yashchenko
 */
public class EntityResponse<T> extends BaseResponse {
    private T entity;

    public EntityResponse(T entity) {
        this.entity = entity;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
