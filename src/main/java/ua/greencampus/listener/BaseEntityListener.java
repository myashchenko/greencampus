package ua.greencampus.listener;

import org.springframework.security.core.context.SecurityContextHolder;
import ua.greencampus.entity.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * @author Mykola Yashchenko
 */
public class BaseEntityListener {

    @PrePersist
    public void setPersistCommonFields(BaseEntity entity) {
        entity.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        entity.setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void setUpdateCommonFields(BaseEntity entity) {
        entity.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
        entity.setUpdatedDate(LocalDateTime.now());
    }
}
