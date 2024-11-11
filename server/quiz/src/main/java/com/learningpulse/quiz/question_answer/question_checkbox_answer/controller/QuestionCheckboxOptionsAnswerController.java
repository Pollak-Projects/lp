package com.learningpulse.quiz.question_answer.question_checkbox_answer.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_options_answer.QuestionCheckboxOptionsAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_options_answer.QuestionCheckboxOptionsAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.service.QuestionCheckboxOptionsAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/checkbox/options")
@RequiredArgsConstructor
public class QuestionCheckboxOptionsAnswerController {
    private final QuestionCheckboxOptionsAnswerService questionCheckboxOptionsAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxOptionsAnswer getQuestionCheckboxOptionsAnswerById(@RequestParam UUID id) {
        return questionCheckboxOptionsAnswerService.getQuestionCheckboxOptionsAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckboxOptionsAnswer> getAllQuestionCheckboxOptionsAnswerByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionCheckboxOptionsAnswerService.getAllQuestionCheckboxOptionsAnswerByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckboxOptionsAnswer> getAllQuestionCheckboxOptionsAnswer() {
        return questionCheckboxOptionsAnswerService.getAllQuestionCheckboxOptionsAnswer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionCheckboxOptionsAnswer createQuestionCheckboxOptionsAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionCheckboxOptionsAnswerCreateDTO questionCheckboxOptionsAnswer) {
        return questionCheckboxOptionsAnswerService.createQuestionCheckboxOptionsAnswer(jwt.getSub(), questionCheckboxOptionsAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxOptionsAnswer updateQuestionCheckboxOptionsAnswer(@RequestBody QuestionCheckboxOptionsAnswerUpdateDTO questionCheckboxOptionsAnswer) {
        return questionCheckboxOptionsAnswerService.updateQuestionCheckboxOptionsAnswer(questionCheckboxOptionsAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxOptionsAnswer deleteQuestionCheckboxOptionsAnswer(@RequestParam UUID id) {
        return questionCheckboxOptionsAnswerService.deleteQuestionCheckboxOptionsAnswer(id);
    }
}
