package com.bailiban.mvc.advice;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ModelAndView handlerException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        ModelAndView modelAndView = null;
        List<String> errMsg = new ArrayList<>();
        String errStr = null;

        BindException be = (BindException) e;
        List<FieldError> errors = be.getBindingResult().getFieldErrors();
        for (FieldError error: errors) {
            errMsg.add(error.getField() + ": " + error.getDefaultMessage());
        }
        errStr = String.join(", ", errMsg);

        boolean isRest = (boolean) request.getAttribute("isRest");
        if (isRest) {
            modelAndView = new ModelAndView(new MappingJackson2JsonView());
            modelAndView.addObject("code", 500);
            modelAndView.addObject("message", errStr);
            return modelAndView;
        }
        Map<String, String> model = new HashMap<>();
        model.put("message", errStr);
        return new ModelAndView("/error/500", model);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException exception) {
        String ret = "handleConstraintViolationException";
        System.out.println(ret);
        return ret;
    }
}
