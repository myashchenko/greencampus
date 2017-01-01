package ua.greencampus.aspect;

import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import ua.greencampus.common.CheckAccess;
import ua.greencampus.common.Messages;
import ua.greencampus.config.AspectOrder;
import ua.greencampus.dto.BaseResponse;
import ua.greencampus.entity.BaseEntity;
import ua.greencampus.entity.Role;
import ua.greencampus.security.EntityAccessCheckService;
import ua.greencampus.service.AuthenticationService;
import javax.persistence.EntityManager;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Mykola Yashchenko
 */
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Aspect
@Order(AspectOrder.ACCESS)
@Component
public class AccessValidationAspect {

    private final AuthenticationService authenticationService;
    private final EntityManager entityManager;
    private final EntityAccessCheckService entityAccessCheckService;

    @Around("@annotation(ua.greencampus.common.CheckAccess) && execution(* *(..))")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Annotation[][] argAnnotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            final Object argument = args[i];

            for (Annotation annotation : argAnnotations[i]) {
                if (PathVariable.class.isInstance(annotation)) {
                    String id = (String) argument;
                    String loggedInUserId = authenticationService.getLoggedInUserId();
                    Role loggedInUserRole = authenticationService.getLoggedInUserRole();
                    if (loggedInUserId == null) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }

                    if (loggedInUserRole == Role.ROLE_ADMIN) {
                        return joinPoint.proceed();
                    }

                    CheckAccess checkAccessAnnotation = method.getDeclaredAnnotation(CheckAccess.class);

                    BaseEntity entity = entityManager.find(checkAccessAnnotation.entityType(), id);

                    if (!entityAccessCheckService.isAllowed(entity)) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new BaseResponse(
                                Messages.ACCESS_DENIED));
                    }
                }
            }
        }

        return joinPoint.proceed();
    }
}
