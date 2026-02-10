package com.meowu.svc.babylon.commons.utils;

import com.meowu.starter.web.commons.mvc.constant.RequestHeaderConstants;
import com.meowu.svc.babylon.commons.security.exception.ResourceDownloadException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.util.Objects;

public class DownloadUtils{

    private DownloadUtils(){
        throw new IllegalStateException("Instantiation is not allowed");
    }

    public static void writeIntoResponse(HttpServletResponse response, RandomAccessUtils.Metadata metadata){
        try(ServletOutputStream outputStream = response.getOutputStream()){
            // content
            byte[] content = metadata.getContent();
            if(Objects.isNull(content)){
                content = new byte[0];
            }
            // set header
            response.setHeader(RequestHeaderConstants.CONTENT_TYPE, getContentType(metadata.getFilename()));
            response.setHeader(RequestHeaderConstants.ACCEPT_RANGES, "bytes");
            response.setHeader(RequestHeaderConstants.CONTENT_LENGTH, String.valueOf(content.length));
            response.setHeader(RequestHeaderConstants.CONTENT_RANGE, "bytes " + metadata.getStartPoint() + "-" + metadata.getEndPoint() + "/" + metadata.getFileSize());
            response.setStatus(HttpStatus.PARTIAL_CONTENT.value());
            // write to response
            outputStream.write(content);
        }catch(Exception e){
            throw new ResourceDownloadException(e.getMessage(), e);
        }
    }

    public static String getContentType(String filename){
        if(StringUtils.isBlank(filename)){
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }

        return MediaTypeFactory.getMediaType(filename)
                               .map(MediaType::toString)
                               .orElse(MediaType.APPLICATION_OCTET_STREAM_VALUE);
    }
}
