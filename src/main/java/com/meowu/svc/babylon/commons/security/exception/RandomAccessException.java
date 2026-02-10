package com.meowu.svc.babylon.commons.security.exception;

import com.meowu.starter.web.commons.security.exception.BusinessException;

public class RandomAccessException extends BusinessException{

    public RandomAccessException(){
        super();
    }

    public RandomAccessException(String message){
        super(message);
    }

    public RandomAccessException(Throwable cause){
        super(cause);
    }

    public RandomAccessException(String message, Throwable cause){
        super(message, cause);
    }
}
