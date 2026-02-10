package com.meowu.svc.babylon.commons.utils;

import com.google.common.collect.Lists;
import com.meowu.starter.common.commons.utils.BooleanUtils;
import com.meowu.starter.common.commons.utils.DigestUtils;
import com.meowu.svc.babylon.commons.constants.enums.ResourceType;
import com.meowu.svc.babylon.commons.security.exception.MediaInfoException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sanselan.ImageInfo;
import org.apache.sanselan.Sanselan;
import org.bytedeco.javacv.FFmpegFrameGrabber;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ResourceInfoUtils{

    private final static List<String> AUDIO_EXTENSION = Lists.newArrayList(
        "flac", "alac", "wav", "aiff",
        "mp3", "aac", "ogg", "wma", "m4a",
        "dsd", "opus", "ac3", "amr"
    );

    private final static List<String> VIDEO_EXTENSION = Lists.newArrayList(
        "mp4", "avi", "mkv", "mov", "wmv",
        "flv", "f4v", "webm", "ts", "m2ts",
        "mpg", "mpeg", "vob", "mxf"
    );

    private final static List<String> PICTURE_EXTENSION = Lists.newArrayList(
        "jpg", "jpeg", "png", "gif",
        "webp", "bmp", "tiff", "tif",
        "psd", "svg", "ai", "pdf", "ico",
        "raw", "dng", "heif", "heic", "avif"
    );

    private final static List<String> DOCUMENT_EXTENSION = Lists.newArrayList(
        "html", "htm", "txt", "json", "md",
        "docx", "xlsx", "pptx", "dotx", "xltx", "potx",
        "doc", "xls", "ppt", "dot", "xlt", "pot",
        "rtf", "mpp", "vsd", "vsdx",
        "odt", "ods", "odp", "odg"
    );

    public static Metadata getMetadata(String path){
        if(StringUtils.isBlank(path)){
            throw new MediaInfoException("MediaInfoUtils: PATH must not be null");
        }

        try{
            // result
            Metadata metadata = new Metadata();

            // get file
            File file = new File(path);

            // basic info
            metadata.setFileName(FilenameUtils.getFilename(path));
            metadata.setPath(file.getAbsolutePath());

            // file info
            metadata.setExtension(FilenameUtils.getExtension(path));
            metadata.setSize(file.length());
            // digest
            metadata.setDigest(DigestUtils.digestToHexString(path, DigestUtils.ALGORITHM_SHA3_512));

            // file attribute
            BasicFileAttributes attributes = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
            // create time
            FileTime createTime = attributes.creationTime();
            if(Objects.nonNull(createTime)){
                metadata.setCreateTime(new Date(createTime.toMillis()));
            }
            // modify time
            FileTime modifyTime = attributes.lastModifiedTime();
            if(Objects.nonNull(modifyTime)){
                metadata.setModifyTime(new Date(modifyTime.toMillis()));
            }

            // media type handler
            switch(getMediaType(metadata.getExtension())){
                case AUDIO -> getAudioInfo(metadata, path);
                case VIDEO -> getVideoInfo(metadata, path);
                case PICTURE -> getPictureInfo(metadata, path);
                case DOCUMENT -> getDocumentInfo(metadata, path);
                case OTHERS -> getOtherTypeInfo(metadata, path);
            }

            return metadata;
        }catch(Exception e){
            throw new MediaInfoException(e.getMessage(), e);
        }
    }

    public static ResourceType getMediaType(String extension){
        if(StringUtils.isBlank(extension)){
            return ResourceType.OTHERS;
        }else if(AUDIO_EXTENSION.contains(extension)){
            return ResourceType.AUDIO;
        }else if(VIDEO_EXTENSION.contains(extension)){
            return ResourceType.VIDEO;
        }else if(PICTURE_EXTENSION.contains(extension)){
            return ResourceType.PICTURE;
        }else if(DOCUMENT_EXTENSION.contains(extension)){
            return ResourceType.DOCUMENT;
        }else{
            return ResourceType.OTHERS;
        }
    }

    private static void getAudioInfo(Metadata metadata, String path){
        try(FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path)){
            // start to grab
            grabber.start();
            // get info
            metadata.setAudioBitRate(grabber.getAudioBitrate());
            metadata.setAudioChannels(grabber.getAudioChannels());
            metadata.setAudioCodecName(grabber.getAudioCodecName());
            metadata.setDuration(grabber.getLengthInTime());
            // stop to grab
            grabber.stop();
            grabber.release();
        }catch(Exception e){
            throw new MediaInfoException(e.getMessage(), e);
        }
    }

    private static void getVideoInfo(Metadata metadata, String path){
        try(FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(path)){
            // start to grab
            grabber.start();
            // get info
            metadata.setVideoBitRate(grabber.getVideoBitrate());
            metadata.setVideoCodecName(grabber.getVideoCodecName());
            metadata.setVideoFps((int) grabber.getVideoFrameRate());
            metadata.setWidth(grabber.getImageWidth());
            metadata.setHeight(grabber.getImageHeight());
            metadata.setDuration(grabber.getLengthInTime());
            metadata.setAudioBitRate(grabber.getAudioBitrate());
            metadata.setAudioChannels(grabber.getAudioChannels());
            metadata.setAudioCodecName(grabber.getAudioCodecName());
            // stop to grab
            grabber.stop();
            grabber.release();
        }catch(Exception e){
            throw new MediaInfoException(e.getMessage(), e);
        }
    }

    private static void getPictureInfo(Metadata metadata, String path){
        try{
            ImageInfo imageInfo = Sanselan.getImageInfo(new File(path));
            metadata.setWidth(imageInfo.getWidth());
            metadata.setHeight(imageInfo.getHeight());
        }catch(Exception e){
            throw new MediaInfoException(e.getMessage(), e);
        }
    }

    private static void getDocumentInfo(Metadata metadata, String path){
    }

    private static void getOtherTypeInfo(Metadata metadata, String path){
    }

    @Getter
    @Setter
    public static class Metadata{

        //------------ Basic ------------//
        private String  path;
        private String  fileName;

        //------------ File ------------//
        private String extension;
        private String digest;
        private Long   size;
        private Date   createTime;
        private Date   modifyTime;

        //------------ Video ------------//
        private Integer videoBitRate;
        private Integer videoFps;
        private String  videoCodecName;

        //------------ Video & Audio ------------//
        private Integer audioBitRate;
        private Integer audioChannels;
        private String  audioCodecName;
        private Long    duration;

        //------------ Picture & Video ------------//
        private Integer width;
        private Integer height;
    }
}
