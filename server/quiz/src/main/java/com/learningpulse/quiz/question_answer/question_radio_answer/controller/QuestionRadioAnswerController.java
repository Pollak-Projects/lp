package com.learningpulse.quiz.question_answer.question_radio_answer.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_answer.QuestionRadioAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_answer.QuestionRadioAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.service.QuestionRadioAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/radio")
@RequiredArgsConstructor
public class QuestionRadioAnswerController {
    private final QuestionRadioAnswerService questionRadioAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioAnswer getQuestionRadioAnswerById(@RequestParam UUID id) {
        return questionRadioAnswerService.getQuestionRadioAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadioAnswer> getAllQuestionRadioAnswerByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionRadioAnswerService.getAllQuestionRadioAnswerByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadioAnswer> getAllQuestionRadioAnswer() {
        return questionRadioAnswerService.getAllQuestionRadioAnswer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionRadioAnswer createQuestionRadioAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionRadioAnswerCreateDTO questionRadioAnswer) {
        return questionRadioAnswerService.createQuestionRadioAnswer(jwt.getSub(), questionRadioAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioAnswer updateQuestionRadioAnswer(@RequestBody QuestionRadioAnswerUpdateDTO questionRadioAnswer) {
        return questionRadioAnswerService.updateQuestionRadioAnswer(questionRadioAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioAnswer deleteQuestionRadioAnswer(@RequestParam UUID id) {
        return questionRadioAnswerService.deleteQuestionRadioAnswer(id);
    }
}
