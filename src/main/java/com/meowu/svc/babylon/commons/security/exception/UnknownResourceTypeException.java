package com.meowu.svc.babylon.commons.security.exception;

import com.meowu.starter.web.commons.security.exception.BusinessException;

public class UnknownResourceTypeException extends BusinessException{

    public UnknownResourceTypeException(){
        super();
    }

    public UnknownResourceTypeException(String message){
        super(message);
    }

    public UnknownResourceTypeException(Throwable cause){
        super(cause);
    }

    public UnknownResourceTypeException(String message, Throwable cause){
        super(message, cause);
    }
}
