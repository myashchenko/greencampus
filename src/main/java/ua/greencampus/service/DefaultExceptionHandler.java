package ua.greencampus.service;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nikolay Yashchenko
 */
@ControllerAdvice(annotations = Controller.class)
public class DefaultExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) throws Exception {
        exception.printStackTrace();
        return null;
    }
}
