package com.learningpulse.classroom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain clientSecurityFilterChain(
            ServerHttpSecurity http,
            JwtConverter jwtAuthenticationConverter) {
        http
                .authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                        .anyExchange().permitAll())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .oauth2ResourceServer(oauth2ResourceServerSpec -> oauth2ResourceServerSpec
                        .jwt(jwtSpec -> jwtSpec
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)));

        return http.build();
    }

    @Bean
    JwtConverter jwtAuthenticationConverter(AuthoritiesConverter authoritiesConverter) {
        return jwt -> Mono.justOrEmpty(new AbstractAuthenticationToken(authoritiesConverter.convert(jwt)) {
            @Override
            public Object getCredentials() {
                return jwt;
            }

            @Override
            public KeycloakJwt getPrincipal() {
                return new KeycloakJwt(jwt);
            }
        });
    }

    @Bean
    AuthoritiesConverter authoritiesConverter() {
        return jwt -> {
            Optional<Map<String, Object>> realmAccess = Optional
                    .ofNullable((Map<String, Object>) jwt.getClaims().get("realm_access"));
            Stream<String> realmRoles = realmAccess
                    .map(map -> (List<String>) map.get("roles"))
                    .stream().flatMap(Collection::stream);

            Stream<String> resourceAccessRoles = Optional
                    .ofNullable((Map<String, Object>) jwt.getClaims().get("resource_access"))
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