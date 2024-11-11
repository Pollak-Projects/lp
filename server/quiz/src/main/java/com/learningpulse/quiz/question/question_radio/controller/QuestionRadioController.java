package com.learningpulse.quiz.question.question_radio.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_radio.dto.question_radio.QuestionRadioCreateDTO;
import com.learningpulse.quiz.question.question_radio.dto.question_radio.QuestionRadioUpdateDTO;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.service.QuestionRadioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/radio")
@RequiredArgsConstructor
public class QuestionRadioController {
    private final QuestionRadioService questionRadioService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadio getQuestionRadioById(@RequestBody UUID id) {
        return questionRadioService.getQuestionRadioById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadio> getAllQuestionRadiosByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionRadioService.getAllQuestionRadiosByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadio> getAllQuestionRadios() {
        return questionRadioService.getAllQuestionRadios();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionRadio createQuestionRadio(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionRadioCreateDTO questionRadio) {
        return questionRadioService.createQuestionRadio(jwt.getSub(), questionRadio);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadio updateQuestionRadio(@RequestBody QuestionRadioUpdateDTO questionRadio) {
        return questionRadioService.updateQuestionRadio(questionRadio);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadio deleteQuestionRadio(@RequestBody UUID id) {
        return questionRadioService.deleteQuestionRadio(id);
    }
}
