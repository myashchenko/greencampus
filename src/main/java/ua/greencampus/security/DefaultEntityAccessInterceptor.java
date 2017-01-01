package ua.greencampus.security;

import org.springframework.security.core.context.SecurityContextHolder;
import ua.greencampus.entity.BaseEntity;

import java.util.Objects;

/**
 * @author Mykola Yashchenko
 */
public class DefaultEntityAccessInterceptor implements EntityAccessInterceptor<BaseEntity> {

    @Override
    public boolean check(BaseEntity entity) {
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        String entityAuthor = entity.getCreatedBy();
        return Objects.equals(currentUser, entityAuthor);
    }

    @Override
    public Class<BaseEntity> getSupportedClass() {
        return BaseEntity.class;
    }
}
