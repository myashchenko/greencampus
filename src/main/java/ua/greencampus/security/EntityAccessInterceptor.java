package ua.greencampus.security;

import ua.greencampus.entity.BaseEntity;

/**
 * @author Mykola Yashchenko
 */
public interface EntityAccessInterceptor<ENTITY extends BaseEntity> {
    boolean check(ENTITY entity);
    Class<ENTITY> getSupportedClass();
}
