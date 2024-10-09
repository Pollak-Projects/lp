package com.learningpulse.quiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaConfig {
    // TODO this is needed for @CreatedDate and @LastModifiedDate, but this below cannot work in webflux so it has to be removed after a while
    @Bean
    public AuditorAware<UUID> auditorAware() {
        return new SpringSecurityAuditorAware();
    }
}