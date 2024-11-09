package com.learningpulse.quiz.quiz;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.quiz.dto.QuizCreateDTO;
import com.learningpulse.quiz.quiz.dto.QuizFullCreateDTO;
import com.learningpulse.quiz.quiz.dto.QuizUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz")
@RequiredArgsConstructor
public class QuizController {
    // TODO Implement this controller
    private final QuizService quizService;
    private static final Logger logger = LoggerFactory.getLogger(QuizController.class);

    // This is just here to show how to use the webclient and interservice communication
    @GetMapping("/webclient")
    @ResponseBody
    public Mono<UserDTO> webclient() {
        return quizService.webclient();
    }

    @GetMapping("/jwtTest")
    @ResponseBody
    public Mono<KeycloakJwt> jwtTest(@AuthenticationPrincipal KeycloakJwt jwt) {
        System.out.println(jwt.getSub());
        return null;
    }

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public Quiz getQuizById(@RequestParam UUID id) {
        return quizService.getQuizById(id);
    }

    // TODO implement
    @GetMapping("/allbyuser")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public List<Quiz> getAllQuizzesByUser(@RequestBody UserDTO user) {
        return null;
    }

    // TODO: remove after testing, is not needed for the final implementation
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz createQuiz(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuizCreateDTO quiz) {
        logger.atDebug().log("Creating quiz", quiz);
        return quizService.createQuiz(jwt.getSub(), quiz);
    }

    @PostMapping("/full")
    @ResponseStatus(HttpStatus.CREATED)
    public Quiz createFullQuiz(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuizFullCreateDTO quiz) {
        return quizService.createFullQuiz(jwt.getSub(), quiz);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Quiz updateQuiz(@RequestBody QuizUpdateDTO quiz) {
        return quizService.updateQuiz(quiz);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public Quiz deleteQuiz(@RequestParam UUID id) {
        return quizService.deleteQuiz(id);
    }

}
