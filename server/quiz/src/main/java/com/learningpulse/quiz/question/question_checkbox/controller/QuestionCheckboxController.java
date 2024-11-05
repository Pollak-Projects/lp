package com.learningpulse.quiz.question.question_checkbox.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.service.QuestionCheckboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/Checkbox")
@RequiredArgsConstructor
public class QuestionCheckboxController {
    private final QuestionCheckboxService questionCheckboxService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckbox getQuestionCheckboxById(@RequestBody UUID id) {
        return questionCheckboxService.getQuestionCheckboxById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckbox> getAllQuestionCheckboxsByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionCheckboxService.getAllQuestionCheckboxsByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckbox> getAllQuestionCheckboxs() {
        return questionCheckboxService.getAllQuestionCheckboxs();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionCheckbox createQuestionCheckbox(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionCheckbox questionCheckbox) {
        return questionCheckboxService.createQuestionCheckbox(jwt.getSub(), questionCheckbox);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckbox updateQuestionCheckbox(@RequestBody QuestionCheckbox questionCheckbox) {
        return questionCheckboxService.updateQuestionCheckbox(questionCheckbox);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckbox deleteQuestionCheckbox(@RequestBody UUID id) {
        return questionCheckboxService.deleteQuestionCheckbox(id);
    }
}
