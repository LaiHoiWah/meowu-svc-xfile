package com.meowu.svc.babylon.commons.security.exception.handler;

import com.meowu.starter.web.commons.security.exception.BusinessException;
import com.meowu.starter.web.commons.security.response.Response;
import com.meowu.starter.web.commons.security.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionAdviceHandler{

    @ExceptionHandler(value = {
        Exception.class,
        RuntimeException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response<?> exception(Exception e){
        log.error(e.getMessage(), e);
        return new Response<>(ResponseCode.FAILURE, "System error.");
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> businessException(RuntimeException e){
        log.error(e.getMessage(), e);
        return new Response<>(ResponseCode.FAILURE, e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public Response<?> illegalArgumentException(IllegalArgumentException e){
        log.error(e.getMessage(), e);
        return new Response<>(ResponseCode.FAILURE, e.getMessage());
    }
}
