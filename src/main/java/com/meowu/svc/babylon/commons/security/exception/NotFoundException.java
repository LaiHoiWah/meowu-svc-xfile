package com.meowu.svc.babylon.commons.security.exception;

import com.meowu.starter.web.commons.security.exception.BusinessException;

public class NotFoundException extends BusinessException{

    public NotFoundException(){
        super();
    }

    public NotFoundException(String message){
        super(message);
    }

    public NotFoundException(Throwable cause){
        super(cause);
    }

    public NotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
