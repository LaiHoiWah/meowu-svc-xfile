package com.meowu.svc.babylon.commons.utils;

import com.meowu.starter.common.commons.utils.AssertUtils;
import com.meowu.svc.babylon.commons.security.exception.RandomAccessException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.ByteArrayOutputStream;
import java.io.RandomAccessFile;

public class RandomAccessUtils{

    private final static String ACCESS_READ_ONLY = "r";

    private RandomAccessUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static Metadata read(String path, long startPoint, long endPoint){
        AssertUtils.isNotBlank(path, "RandomAccessUtils: PATH must not be null");

        // read only
        try(
            RandomAccessFile      file   = new RandomAccessFile(path, ACCESS_READ_ONLY);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
        ){
            String filename = FilenameUtils.getFullName(path);

            if(startPoint > file.length() || startPoint < 0){
                return new Metadata(filename, new byte[0], 0, 0, 0);
            }

            if(endPoint < 0){
                endPoint = file.length() - startPoint - 1;
            }

            // 8kb buffer
            byte[] buffer = new byte[8192];

            long currentOffset = startPoint;
            long remaining     = endPoint - startPoint + 1;
            int  bytesRead     = 0;

            while(remaining > 0){
                // seek
                file.seek(currentOffset);
                // read
                bytesRead = file.read(buffer);
                // nothing to read
                if(bytesRead == -1){
                    break;
                }

                // write into stream
                output.write(buffer, 0, bytesRead);

                // reset
                currentOffset += bytesRead;
                remaining     -= bytesRead;
            }

            return new Metadata(filename, output.toByteArray(), startPoint, endPoint - remaining, file.length());
        }catch(Exception e){
            throw new RandomAccessException(e.getMessage(), e);
        }
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Metadata{

        private String filename;
        private byte[] content;
        private long startPoint;
        private long endPoint;
        private long fileSize;
    }
}
