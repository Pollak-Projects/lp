package com.learningpulse.quiz;

import com.learningpulse.quiz.external.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class QuizService {
    private final WebClient webClient;

    public Mono<UserDTO> webclient() {
        return webClient.get()
                .uri("http://localhost:8181/api/v1/user/webclient")
                .retrieve()
                .bodyToMono(UserDTO.class);
    }
}
