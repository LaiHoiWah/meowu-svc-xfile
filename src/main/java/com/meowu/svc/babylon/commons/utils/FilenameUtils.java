package com.meowu.svc.babylon.commons.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class FilenameUtils{

    private FilenameUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static String getFullName(String path){
        if(StringUtils.isBlank(path)){
            return StringUtils.EMPTY;
        }

        int separatorIndex = path.lastIndexOf(File.separator);

        return (separatorIndex == -1 ? path : path.substring(separatorIndex + 1));
    }

    public static String getFilename(String path){
        if(StringUtils.isBlank(path)){
            return StringUtils.EMPTY;
        }

        int separatorIndex = path.lastIndexOf(File.separator);
        String filename = (separatorIndex == -1 ? path : path.substring(separatorIndex + 1));

        int dotIndex = filename.lastIndexOf(".");
        return dotIndex == -1 ? filename : filename.substring(0, dotIndex);
    }

    public static String getExtension(String path){
        if(StringUtils.isBlank(path)){
            return StringUtils.EMPTY;
        }

        File file = new File(path);
        if(file.isDirectory()){
            return StringUtils.EMPTY;
        }

        int dotIndex = path.lastIndexOf(".");
        return dotIndex == -1 ? StringUtils.EMPTY : path.substring(dotIndex + 1);
    }
}
