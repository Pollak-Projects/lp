package com.learningpulse.quiz;

import com.learningpulse.quiz.external.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
@RequiredArgsConstructor
public class QuizService {
    private final WebClient webClient;
    public UserDTO webclient() {
        return webClient.get()
                .uri("http://localhost:8080/api/v1/user/webclient")
                .retrieve()
                .bodyToFlux(UserDTO.class)
                // TODO make this non blocking
                .blockFirst();
    }
}
