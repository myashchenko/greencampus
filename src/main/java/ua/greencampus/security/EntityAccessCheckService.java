package ua.greencampus.security;

import org.springframework.stereotype.Component;
import ua.greencampus.entity.BaseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mykola Yashchenko
 */
@Component
public class EntityAccessCheckService {

    private final Map<Class<? extends BaseEntity>, EntityAccessInterceptor<? extends BaseEntity>> interceptors = new HashMap<>();

    public EntityAccessCheckService(List<EntityAccessInterceptor<? extends BaseEntity>> interceptors) {
        for (EntityAccessInterceptor<? extends BaseEntity> interceptor : interceptors) {
            this.interceptors.put(interceptor.getSupportedClass(), interceptor);
        }
    }

    public <T extends BaseEntity> boolean isAllowed(T entity) {
        EntityAccessInterceptor<? extends BaseEntity> interceptor = interceptors.get(entity.getClass());

        if (interceptor != null) {
//            interceptor.check(entity);
        }

        return true;
    }
}
