package com.learningpulse.quiz.quiz;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.external.UserDTO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final QuizRepository quizRepository;
    private final WebClient webClient;

    public Mono<UserDTO> webclient() {

        return webClient
                .get()
                .uri("http://localhost:8181/api/v1/user/webclient")
                .attributes(ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("okta"))
                .retrieve()
                .bodyToMono(UserDTO.class);
    }

    public Quiz getQuizById(UUID id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND));
    }

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = quizRepository.findAll();
        if (quizzes.isEmpty())
            throw new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND);
        return quizzes;
    }

    public Quiz createQuiz(UUID sub, @NotNull Quiz quiz) {
        quiz.setCreatedBy(sub);
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(@NotNull Quiz quiz) {
        return quizRepository.findById(quiz.getId())
                .map(q -> quizRepository.save(quiz))
                .orElseThrow(() -> new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND));
    }

    public Quiz deleteQuiz(UUID id) {
        return quizRepository.findById(id)
                .map(q -> {
                    quizRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("Quiz not found", HttpStatus.NOT_FOUND));
    }
}
