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
            JwtConverter jwtAuthenticationConverter) throws Exception {
        http
                .oauth2ResourceServer(oauth2ResourceServerSpec -> oauth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec
                                .jwtAuthenticationConverter(
                                        jwtAuthenticationConverter)))
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

                        // For microservice API documentation endpoints
                        .pathMatchers("GET", "/api/v1/dummy/v3/api-docs/**").permitAll()
                        .pathMatchers("GET", "/api/v1/drive/v3/api-docs/**").permitAll()
                        .pathMatchers("GET", "/api/v1/quiz/v3/api-docs/**").permitAll()
                        .pathMatchers("GET", "/api/v1/user/v3/api-docs/**").permitAll()
                        .pathMatchers("GET", "/api/v1/classroom/v3/api-docs/**").permitAll()


                        // For favicon
                        .pathMatchers("/favicon.ico").permitAll()

                        // The rest
                        .pathMatchers("/api/v1/quiz/webclient").hasAnyRole(
                                "test_role",
                                "client-credentials-test")

                        .pathMatchers("/api/v1/user/webclient").hasAnyRole(
                                "test_role",
                                "client-credentials-test")

                        // Anything else
                        .anyExchange().authenticated());

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(
                Arrays.asList("Authorization", "Cache-Control", "Content-Type",
                        "Access-Control-Allow-Origin",
                        "Connection", "Accept", "Origin", "X-Requested-With",
                        "Access-Control-Request-Method",
                        "Access-Control-Request-Headers", "Access-Control-Allow-Credentials"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    JwtConverter jwtAuthenticationConverter(AuthoritiesConverter authoritiesConverter) {
        var authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(
                jwt -> authoritiesConverter.convert(jwt.getClaims()));
        return jwt -> Mono.justOrEmpty(authenticationConverter.convert(jwt));
    }

    @Bean
    AuthoritiesConverter authoritiesConverter() {
        return claims -> {
            Optional<Map<String, Object>> realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
            Stream<String> realmRoles = realmAccess
                    .map(map -> (List<String>) map.get("roles"))
                    .stream().flatMap(Collection::stream);

            Stream<String> resourceAccessRoles = Optional.ofNullable((Map<String, Object>) claims.get("resource_access"))
                    .map(Map::values)
                    .stream()
                    .flatMap(Collection::stream)
                    .map(Map.class::cast)
                    .flatMap(client -> Optional.ofNullable((List<String>) client.get("roles")).stream())
                    .flatMap(Collection::stream);

            List<String> roles = Stream.concat(realmRoles, resourceAccessRoles).toList();


            return roles
                    .stream()
                    .map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .map(GrantedAuthority.class::cast)
                    .collect(Collectors.toList());

        };
    }
}
