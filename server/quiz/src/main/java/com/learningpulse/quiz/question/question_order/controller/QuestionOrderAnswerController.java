package com.learningpulse.quiz.question.question_order.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question.question_order.service.QuestionOrderAnswerService;
import com.learningpulse.quiz.question.question_order.service.QuestionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/order")
@RequiredArgsConstructor
public class QuestionOrderAnswerController {
    // TODO: Implement this controller
    private final QuestionOrderAnswerService questionOrderAnswerService;
    private final QuestionOrderService questionOrderService;

    @GetMapping( params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderAnswer getQuestionOrderAnswerById(@RequestParam UUID id) {
        return questionOrderAnswerService.getQuestionOrderAnswerById(id);
    }

    @GetMapping("/all")
    @ResponseBody
    public List<QuestionOrderAnswer> getAllQuestionOrderAnswersByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionOrderAnswerService.getAllQuestionOrderAnswerByUser(jwt.getSub());
    }

    @GetMapping(value = "/", params = "questionOrderId")
    @ResponseBody
    public Mono<Iterable<QuestionOrderAnswer>> getAllQuestionOrderAnswersByQuestion(@RequestParam UUID questionOrderId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionOrderAnswer> createQuestionOrderAnswer(@RequestBody QuestionOrderAnswer questionOrderAnswer) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionOrderAnswer> updateQuestionOrderAnswer(@RequestBody QuestionOrderAnswer questionOrderAnswer) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionOrderAnswer> deleteQuestionOrderAnswer(@RequestParam UUID id) {
        return null;
    }
}
