package com.meowu.svc.babylon.commons.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Getter
@Setter
public class ResourceProperties{

    @Value("${spring.servlet.file-dir.audio}")
    private String audioDir;

    @Value("${spring.servlet.file-dir.video}")
    private String videoDir;

    @Value("${spring.servlet.file-dir.document}")
    private String documentDir;

    @Value("${spring.servlet.file-dir.picture}")
    private String pictureDir;

    @Value("${spring.servlet.file-dir.others}")
    private String othersDir;
}
