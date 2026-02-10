package com.meowu.svc.babylon.commons.security.exception;

import com.meowu.starter.web.commons.security.exception.BusinessException;

public class ResourceDownloadException extends BusinessException{

    public ResourceDownloadException(){
        super();
    }

    public ResourceDownloadException(String message){
        super(message);
    }

    public ResourceDownloadException(Throwable cause){
        super(cause);
    }

    public ResourceDownloadException(String message, Throwable cause){
        super(message, cause);
    }
}
