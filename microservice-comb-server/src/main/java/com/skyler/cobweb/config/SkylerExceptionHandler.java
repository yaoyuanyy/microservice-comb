package com.skyler.cobweb.config;

import com.skyler.cobweb.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Set;


@RestControllerAdvice
@Slf4j
public class SkylerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(SkylerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    protected ResponseResult handleException(Exception ex) {

        log.warn("ex:", ex);

        if (ex instanceof MethodArgumentNotValidException) {
             return handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex);
        } else if (ex instanceof IllegalArgumentException) {
             return handleIllegalArgumentException((IllegalArgumentException) ex);
        } else if (ex instanceof ConstraintViolationException) {
             return handleConstraintViolationException((ConstraintViolationException) ex);
        }

        logger.error("[ExceptionHandler 服务异常] ex:{}", ex.getMessage(), ex);
        if (ex instanceof BindException) {
            return handleBindExceptionException((BindException) ex);
        } else {
            return otherException(ex);
        }
    }

    private ResponseResult handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder strBuilder = new StringBuilder();
        Iterator<ConstraintViolation<?>> it = violations.iterator();
        if (it.hasNext()) {
            strBuilder.append(it.next().getMessage());
        }
        String errorMsg = strBuilder.toString();
        return new ResponseResult().fail(errorMsg);
    }

     private ResponseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = message(ex.getBindingResult());
        return new ResponseResult().fail(message);
    }

     private ResponseResult handleBindExceptionException(BindException ex) {
        String message = message(ex.getBindingResult());
        return new ResponseResult().fail(message);
    }

    private String message(BindingResult result) {
        if (result == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        result.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            sb.append(fieldName).append(" ").append(errorMessage).append(".");
        });

        return sb.toString();
    }

    private ResponseResult handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseResult().fail(ex.getMessage());
    }


    private ResponseResult otherException(Exception ex) {
        return new ResponseResult().fail(500, ex.getMessage());
    }
}
