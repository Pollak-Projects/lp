package com.learningpulse.quiz.question.question_radio.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question.question_radio.service.QuestionRadioOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/radio/options")
@RequiredArgsConstructor
public class QuestionRadioOptionsController {
    private final QuestionRadioOptionsService questionRadioOptionsService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioOptions getQuestionRadioOptionsById(@RequestParam UUID id) {
        return questionRadioOptionsService.getQuestionRadioOptionsById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadioOptions> getAllQuestionRadioOptionsByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionRadioOptionsService.getAllQuestionRadioOptionsByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadioOptions> getAllQuestionRadioOptions(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionRadioOptionsService.getAllQuestionRadioOptions();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionRadioOptions createQuestionRadioOptions(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionRadioOptions questionRadioOptions) {
        return questionRadioOptionsService.createQuestionRadioOptions(jwt.getSub(), questionRadioOptions);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioOptions updateQuestionRadioOptions(@RequestBody QuestionRadioOptions questionRadioOptions) {
        return questionRadioOptionsService.updateQuestionRadioOptions(questionRadioOptions);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioOptions deleteQuestionRadioOptions(@RequestParam UUID id) {
        return questionRadioOptionsService.deleteQuestionRadioOptions(id);
    }
}
