package com.learningpulse.quiz.question_answer.question_checkbox_answer.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.service.QuestionCheckboxAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/Checkbox")
@RequiredArgsConstructor
public class QuestionCheckboxAnswerController {
    private final QuestionCheckboxAnswerService questionCheckboxAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxAnswer getQuestionCheckboxAnswerById(@RequestParam UUID id) {
        return questionCheckboxAnswerService.getQuestionCheckboxAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckboxAnswer> getAllQuestionCheckboxAnswerByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionCheckboxAnswerService.getAllQuestionCheckboxAnswerByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionCheckboxAnswer> getAllQuestionCheckboxAnswer() {
        return questionCheckboxAnswerService.getAllQuestionCheckboxAnswer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionCheckboxAnswer createQuestionCheckboxAnswer(@RequestBody QuestionCheckboxAnswer questionCheckboxAnswer) {
        return questionCheckboxAnswerService.createQuestionCheckboxAnswer(questionCheckboxAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxAnswer updateQuestionCheckboxAnswer(@RequestBody QuestionCheckboxAnswer questionCheckboxAnswer) {
        return questionCheckboxAnswerService.updateQuestionCheckboxAnswer(questionCheckboxAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionCheckboxAnswer deleteQuestionCheckboxAnswer(@RequestParam UUID id) {
        return questionCheckboxAnswerService.deleteQuestionCheckboxAnswer(id);
    }
}
