package com.learningpulse.classroom.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Configuration
public class OpenAPIConfiguration {
    @Bean
    public OpenAPI classroomOpenAPI() {
        return new OpenAPI()
                .servers(
                        List.of(
                                new Server().url("localhost:8081").description("Development server")
                        )
                )
                .info(new Info()
                        .title("Learning Pulse API documentation")
                        .version("0.0.1")
                        .description("This is the API documentation for the classroom microservice.")
                        .contact(new Contact()
                                .name("Learning Pulse")
                                .url("https://https://github.com/Pollak-Projects/lp"))
                );
    }
}