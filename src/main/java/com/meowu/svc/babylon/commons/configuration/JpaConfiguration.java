package com.meowu.svc.babylon.commons.configuration;

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Optional;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EntityScan(basePackages = "com.meowu.svc.babylon.core.**.entity")
@EnableJpaRepositories(
    basePackages = "com.meowu.svc.babylon.core.**.dao.repository",
    repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean.class
)
public class JpaConfiguration{

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public AuditorAware<Long> auditorProvider(){
        return new AuditorAware<Long>(){
            @Override
            public Optional<Long> getCurrentAuditor(){
                return Optional.of(1L);
            }
        };
    }
}
