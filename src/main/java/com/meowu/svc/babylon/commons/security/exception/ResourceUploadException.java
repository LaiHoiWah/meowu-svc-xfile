package com.meowu.svc.babylon.commons.security.exception;

import com.meowu.starter.web.commons.security.exception.BusinessException;

public class ResourceUploadException extends BusinessException{

    public ResourceUploadException(){
        super();
    }

    public ResourceUploadException(String message){
        super(message);
    }

    public ResourceUploadException(Throwable cause){
        super(cause);
    }

    public ResourceUploadException(String message, Throwable cause){
        super(message, cause);
    }
}
