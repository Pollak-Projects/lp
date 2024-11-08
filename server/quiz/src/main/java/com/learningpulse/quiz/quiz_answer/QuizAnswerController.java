package com.learningpulse.quiz.quiz_answer;

import com.learningpulse.quiz.config.KeycloakJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer")
@RequiredArgsConstructor
public class QuizAnswerController {
    private final QuizAnswerService quizAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuizAnswer getQuizAnswerById(@RequestParam UUID id) {
        return quizAnswerService.getQuizAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuizAnswer> getQuizAnswerByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return quizAnswerService.getAllQuizAnswersByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuizAnswer> getAllQuizAnswers() {
        return quizAnswerService.getAllQuizAnswers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuizAnswer createQuizAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuizAnswer quizAnswer) {
        return quizAnswerService.createQuizAnswer(jwt.getSub(), quizAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuizAnswer updateQuizAnswer(@RequestBody QuizAnswer quizAnswer) {
        return quizAnswerService.updateQuizAnswer(quizAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuizAnswer deleteQuizAnswer(@RequestParam UUID id) {
        return quizAnswerService.deleteQuizAnswer(id);
    }
}
