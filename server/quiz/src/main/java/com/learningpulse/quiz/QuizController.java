package com.learningpulse.quiz;

import com.learningpulse.quiz.external.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    // This is just here to show how to use the webclient and interservice communication
    @GetMapping("/webclient")
    public @ResponseBody Mono<UserDTO> webclient() {
        return quizService.webclient();
    }


}
