package com.learningpulse.quiz.question.question_order.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_order.dto.question_order_options.QuestionOrderOptionsCreateDTO;
import com.learningpulse.quiz.question.question_order.dto.question_order_options.QuestionOrderOptionsUpdateDTO;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question.question_order.service.QuestionOrderOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/order/options")
@RequiredArgsConstructor
public class QuestionOrderOptionsController {
    private final QuestionOrderOptionsService questionOrderOptionsService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderOptions getQuestionOrderOptionsById(@RequestParam UUID id) {
        return questionOrderOptionsService.getQuestionOrderOptionsById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrderOptions> getAllQuestionOrderOptionsByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionOrderOptionsService.getAllQuestionOrderOptionsByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrderOptions> getAllQuestionOrderOptions() {
        return questionOrderOptionsService.getAllQuestionOrderOptions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionOrderOptions createQuestionOrderOptions(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionOrderOptionsCreateDTO questionOrderOptions) {
        return questionOrderOptionsService.createQuestionOrderOptions(jwt.getSub(), questionOrderOptions);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderOptions updateQuestionOrderOptions(@RequestBody QuestionOrderOptionsUpdateDTO questionOrderOptions) {
        return questionOrderOptionsService.updateQuestionOrderOptions(questionOrderOptions);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderOptions deleteQuestionOrderOptions(@RequestParam UUID id) {
        return questionOrderOptionsService.deleteQuestionOrderOptions(id);
    }
}
