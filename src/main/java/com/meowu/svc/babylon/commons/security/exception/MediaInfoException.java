package com.meowu.svc.babylon.commons.security.exception;

import com.meowu.starter.web.commons.security.exception.BusinessException;

public class MediaInfoException extends BusinessException{

    public MediaInfoException(){
        super();
    }

    public MediaInfoException(String message){
        super(message);
    }

    public MediaInfoException(Throwable cause){
        super(cause);
    }

    public MediaInfoException(String message, Throwable cause){
        super(message, cause);
    }
}
