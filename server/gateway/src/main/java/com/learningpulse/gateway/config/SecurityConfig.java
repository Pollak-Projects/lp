package com.learningpulse.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebFluxSecurity
@Profile("!development")
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain clientSecurityFilterChain(
            ServerHttpSecurity http,
            JwtConverter jwtAuthenticationConverter
    ) throws Exception {
        http
                .oauth2ResourceServer(oauth2ResourceServerSpec -> oauth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                )
                .cors(corsSpec -> corsSpec.configurationSource(corsConfigurationSource()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(requests -> requests

                        // For eureka and actuator endpoints
                        .pathMatchers("/eureka/**").permitAll()
                        .pathMatchers("/actuator/**").permitAll()

                        // API documentation endpoints
                        .pathMatchers("GET", "/swagger-ui.html").permitAll()
                        .pathMatchers("GET", "/swagger-resources/**").permitAll()
                        .pathMatchers("GET", "/v3/api-docs/**").permitAll()
                        .pathMatchers("GET", "/webjars/**").permitAll()
                        .pathMatchers(
                                "/favicon.ico"
                        ).permitAll()

                        // The rest
                        .pathMatchers("/api/v1/quiz/webclient").authenticated()//.hasAnyRole("test_role", "client_test_role")
                        .pathMatchers("/api/v1/user/webclient").authenticated()//.hasAnyRole("test_role", "client_test_role")

                        // Anything else
                        .anyExchange().authenticated()
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(
                Arrays.asList("Authorization", "Cache-Control", "Content-Type", "Access-Control-Allow-Origin",
                        "Connection", "Accept", "Origin", "X-Requested-With", "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Access-Control-Allow-Credentials"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    JwtConverter jwtAuthenticationConverter(AuthoritiesConverter authoritiesConverter) {
        var authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> authoritiesConverter.convert(jwt.getClaims()));
        return jwt -> Mono.justOrEmpty(authenticationConverter.convert(jwt));
    }

    @Bean
    AuthoritiesConverter realmRolesAuthoritiesConverter() {
        return claims -> {
            var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
            var roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
            return roles.stream()
                    .flatMap(Collection::stream)
                    .map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .map(GrantedAuthority.class::cast)
                    .collect(Collectors.toList());
        };
    }
}
