package com.learningpulse.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

public class ClientSecurityFilterChain {
    @Bean
    SecurityFilterChain clientSecurityFilterChain(
            HttpSecurity http,
            ClientRegistrationRepository clientRegistrationRepository
    ) throws Exception {
        http.oauth2Login(Customizer.withDefaults());
        http.logout((logout) -> {
           var logoutSuccessHandler = new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
           logoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/");
           logout.logoutSuccessHandler(logoutSuccessHandler);
        });

        http.authorizeHttpRequests(requests -> {
            requests.requestMatchers(
                    "/",
                    "/favicon.ico"
                    ).permitAll();
            requests.requestMatchers("/nice").hasAnyAuthority("SCOPE_nice");
            requests.anyRequest().denyAll();
        });

        return http.build();
    }
}
