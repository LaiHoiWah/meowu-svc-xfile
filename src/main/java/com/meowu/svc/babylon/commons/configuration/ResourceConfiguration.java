package com.meowu.svc.babylon.commons.configuration;

import com.meowu.svc.babylon.commons.configuration.properties.ResourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ResourceProperties.class)
public class ResourceConfiguration{

}
