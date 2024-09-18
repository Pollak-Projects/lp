package com.learningpulse.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers.anyExchange;

@Configuration
@EnableWebFluxSecurity
@Profile("!development")
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain clientSecurityFilterChain(
            ServerHttpSecurity http,
            ClientRegistrationRepository clientRegistrationRepository
    ) throws Exception {
        http.oauth2Login(Customizer.withDefaults());
        http.logout((logout) -> {
            var logoutSuccessHandler = new ServerLogoutSuccessHandler() {
                @Override
                public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
                    return null;
                }
            };
            logout.logoutSuccessHandler(logoutSuccessHandler);
        });

        http.authorizeExchange(requests -> requests
                .pathMatchers(
                        "/",
                        "/favicon.ico"
                ).permitAll()
                .pathMatchers("/nice").hasAnyAuthority("SCOPE_nice")
                .anyExchange().denyAll()
        );

        return http.build();
    }

    // TODO fix xd
//    @Bean
//    GrantedAuthoritiesMapper authenticationConverter(
//            Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter) {
//        return (authorities) -> authorities.stream()
//                .filter(authority -> authority instanceof OidcUserAuthority)
//                .map(OidcUserAuthority.class::cast)
//                .map(OidcUserAuthority::getIdToken)
//                .map(OidcIdToken::getClaims)
//                .map(authoritiesConverter.convert(Jwt))
//                .flatMap(roles -> roles.stream())
//                .collect(Collectors.toSet());
//    }
//
//    @Bean
//    JwtAuthenticationConverter jwtAuthenticationConverter(
//            Converter<Jwt, Collection<GrantedAuthority>> authoritiesConverter) {
//        var authenticationConverter = new JwtAuthenticationConverter();
//        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            return authoritiesConverter.convert(jwt.getClaims());
//        });
//        return authenticationConverter;
//    }
//
//    @Bean
//    AuthoritiesConverter realmRolesAuthoritiesConverter() {
//        return claims -> {
//            var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
//            var roles = realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
//            return roles.map(List::stream)
//                    .orElse(Stream.empty())
//                    .map(SimpleGrantedAuthority::new)
//                    .map(GrantedAuthority.class::cast)
//                    .toList();
//        };
//    }
}
