package com.learningpulse.gateway.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;
import java.util.UUID;

class SpringSecurityAuditorAware implements AuditorAware<UUID> {

    @Override
    @NotNull
    public Optional<UUID> getCurrentAuditor() {

        // TODO this will need fixing, it currently doesn't search for the correct value in the decompiled JWT token
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(UUID.class::cast);
    }
}