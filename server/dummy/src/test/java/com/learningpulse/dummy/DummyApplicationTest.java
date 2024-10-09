package com.learningpulse.dummy;

import com.learningpulse.dummy.services.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;


@ExtendWith(SpringExtension.class)
@WebFluxTest(TestController.class)
@ContextConfiguration(classes = TestController.class)
@AutoConfigureWebTestClient
@WithMockUser
class DummyApplicationTest {
    @Autowired
    private ApplicationContext webApplicationContext;

    private WebTestClient webClient;

    @MockBean
    private GreetingService greetingService;


    @BeforeEach
    void setup() {
        webClient = WebTestClient
                .bindToApplicationContext(webApplicationContext)
                .apply(springSecurity())
                .configureClient()
                .build();
    }

    @Test
    void shouldReturnDefaultMessage() throws Exception {
        this.webClient
                .get()
                .uri("/api/v1/dummy/test")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, World!");
    }

    @Test
    void greetingShouldReturnMessageFromService() throws Exception {
        when(greetingService.greet()).thenReturn("I work!");
        this.webClient
                .get()
                .uri("/api/v1/dummy/greeting")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("I work!");
    }
}
