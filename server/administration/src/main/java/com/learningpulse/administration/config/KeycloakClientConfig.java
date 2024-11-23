package com.learningpulse.administration.config;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakClientConfig {
    @Value("${KC_URL}")
    private String keycloakUrl;

    @Value("${KC_REALM}")
    private String realm;

    @Value("${KC_CLIENT_ID}")
    private String clientId;

    @Value("${KC_CLIENT_SECRET}")
    private String clientSecret;

    @Bean
    Keycloak keycloak() {
        ResteasyClientBuilder resteasyClientBuilder = new ResteasyClientBuilderImpl();
        return KeycloakBuilder.builder()
                .serverUrl(keycloakUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .resteasyClient(resteasyClientBuilder.connectionPoolSize(10).build())
                .build();
    }
}