package ua.greencampus.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ua.greencampus.common.Messages;
import ua.greencampus.config.AspectOrder;
import ua.greencampus.dto.BaseResponse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * @author Mykola Yashchenko
 */
@Aspect
@Order(AspectOrder.VALIDATION)
@Component
public class ValidationAspect {

    private static final String ID_PARAM = "id";
    private List<Validator> validators;

    @Autowired
    public ValidationAspect(List<Validator> validators) {
        this.validators = validators;
    }

    @Around("execution(* ua.greencampus.web.rest..*.*(..))")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Annotation[][] argAnnotations = method.getParameterAnnotations();
        String[] argNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        for (int i = 0; i < args.length; i++) {
            final Object argument = args[i];

            for (Annotation annotation : argAnnotations[i]) {
                if (RequestBody.class.isInstance(annotation)) {
                    MapBindingResult bindingResult = new MapBindingResult(new HashMap<>(), argNames[i]);
                    validators.stream().filter(v -> v.supports(argument.getClass())).forEach(v -> v.validate(argument, bindingResult));
                    if (bindingResult.hasErrors()) {
                        return ResponseEntity.badRequest().body(new BaseResponse(bindingResult));
                    }
                } else if (PathVariable.class.isInstance(annotation)) {
                    PathVariable pathVariable = (PathVariable) annotation;
//                    if (ID_PARAM.equals(pathVariable.value())) {
//                        String id = (String) argument;
//                        if (id == null || id <= 0L) {
//                            return ResponseEntity.badRequest().body(new BaseResponse(Messages.ID_INCORRECT));
//                        }
//                    }
                }
            }
        }

        return joinPoint.proceed(args);
    }
}
