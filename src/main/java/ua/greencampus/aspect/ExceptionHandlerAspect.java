package ua.greencampus.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ua.greencampus.dto.BaseResponse;

/**
 * @author Mykola Yashchenko
 */
@Aspect
@Component
public class ExceptionHandlerAspect {

    @Around("execution(* ua.greencampus.web.rest..*.*(..))")
    public Object exceptionHandlerWithReturnType(ProceedingJoinPoint joinPoint) throws Throwable{
        try {
            return joinPoint.proceed();
        } catch(Exception ex) {
            BaseResponse errorResponse = new BaseResponse();
            errorResponse.putError("500", "Internal Server Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
