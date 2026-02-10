package com.meowu.svc.babylon.commons.configuration;

import com.meowu.starter.common.commons.utils.SnowflakeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowflakeConfiguration{

    @Bean
    public SnowflakeUtils filenameSnowflakeUtils(){
        return new SnowflakeUtils(0);
    }

    @Bean
    public SnowflakeUtils archiveNoSnowflakeUtils(){
        return new SnowflakeUtils(1);
    }
}
