package com.meowu.svc.babylon.commons.security.exception;

import com.meowu.starter.web.commons.security.exception.BusinessException;

public class DownloadException extends BusinessException{

    public DownloadException(){
        super();
    }

    public DownloadException(String message){
        super(message);
    }

    public DownloadException(Throwable cause){
        super(cause);
    }

    public DownloadException(String message, Throwable cause){
        super(message, cause);
    }
}
