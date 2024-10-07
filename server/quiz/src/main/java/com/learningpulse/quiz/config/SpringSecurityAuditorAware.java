package com.learningpulse.quiz.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

import java.util.Optional;
import java.util.UUID;

@Configuration
class SpringSecurityAuditorAware implements AuditorAware<UUID> {

    @Override
    @NotNull
    public Optional<UUID> getCurrentAuditor() {
        System.out.println("ljaksfjkhlsajsadfjlksjdfkljklsadljkasdfjlksadf");
        System.out.println("asdfasfd");

        System.out.println();

        System.out.println("gasfjhjdgsfdsfgjlhk");
        System.out.println(ReactiveSecurityContextHolder.getContext()
                .share()
                .block());

        // https://stackoverflow.com/questions/51449889/block-blockfirst-blocklast-are-blocking-error-when-calling-bodytomono-afte
        return Optional.ofNullable(ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .map(KeycloakJwt.class::cast)
                .map(KeycloakJwt::getSub)
                .share()
                .block());
    }
}