package com.learningpulse.quiz.question.question_checkbox.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options.QuestionCheckboxOptionsCreateDTO;
import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options.QuestionCheckboxOptionsUpdateDTO;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question.question_checkbox.service.QuestionCheckboxOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/checkbox/options")
@RequiredArgsConstructor
public class QuestionCheckboxOptionsController {
    private final QuestionCheckboxOptionsService questionCheckboxOptionsService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxOptions getQuestionCheckboxOptionsById(@RequestParam UUID id) {
        return questionCheckboxOptionsService.getQuestionCheckboxOptionsById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckboxOptions> getAllQuestionCheckboxOptionsByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionCheckboxOptionsService.getAllQuestionCheckboxOptionsByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckboxOptions> getAllQuestionCheckboxOptions() {
        return questionCheckboxOptionsService.getAllQuestionCheckboxOptions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionCheckboxOptions createQuestionCheckboxOptions(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionCheckboxOptionsCreateDTO questionCheckboxOptions) {
        return questionCheckboxOptionsService.createQuestionCheckboxOptions(jwt.getSub(), questionCheckboxOptions);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxOptions updateQuestionCheckboxOptions(@RequestBody QuestionCheckboxOptionsUpdateDTO questionCheckboxOptions) {
        return questionCheckboxOptionsService.updateQuestionCheckboxOptions(questionCheckboxOptions);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxOptions deleteQuestionCheckboxOptions(@RequestParam UUID id) {
        return questionCheckboxOptionsService.deleteQuestionCheckboxOptions(id);
    }
}
