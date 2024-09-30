package com.learningpulse.quiz;

import com.learningpulse.quiz.external.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {
    // TODO Implement this controller
    private final QuizService quizService;

    // This is just here to show how to use the webclient and interservice communication
    @GetMapping("/webclient")
    @ResponseBody
    public Mono<UserDTO> webclient() {
        return quizService.webclient();
    }

    @GetMapping("/")
    @ResponseBody
    public Mono<Quiz> getQuizById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<Quiz>> getAllQuizzesByUser(@RequestBody UserDTO user) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<Quiz> createQuiz(@RequestBody Quiz quiz) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<Quiz> updateQuiz(@RequestBody Quiz quiz) {
        return null;
    }


    @DeleteMapping("/")
    @ResponseBody
    public Mono<Quiz> deleteQuiz(@RequestParam UUID id) {
        return null;
    }

}
