package com.learningpulse.administration.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI quizOpenAPI() {
        String schemeName = "bearerAuth";
        String bearerFormat = "JWT";
        String scheme = "bearer";
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("http://localhost:8181").description("Development server")
                        )
                )
                .info(new Info()
                        .title("Learning Pulse API documentation")
                        .version("0.0.1")
                        .description("This is the API documentation for the administration microservice.")
                        .contact(new Contact()
                                .name("Learning Pulse")
                                .url("https://github.com/pollak-projects/lp"))
                )
                .addSecurityItem(new SecurityRequirement().addList(schemeName))
                .components(new Components()
                        .addSecuritySchemes(
                                schemeName,
                                new SecurityScheme()
                                        .name(schemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat(bearerFormat)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme(scheme)
                        ));
    }
}