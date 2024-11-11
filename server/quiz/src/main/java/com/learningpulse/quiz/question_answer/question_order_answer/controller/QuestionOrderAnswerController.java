package com.learningpulse.quiz.question_answer.question_order_answer.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_order.service.QuestionOrderService;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_answer.QuestionOrderAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_answer.QuestionOrderAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.service.QuestionOrderAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/order")
@RequiredArgsConstructor
public class QuestionOrderAnswerController {
    private final QuestionOrderAnswerService questionOrderAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderAnswer getQuestionOrderAnswerById(@RequestParam UUID id) {
        return questionOrderAnswerService.getQuestionOrderAnswerById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrderAnswer> getAllQuestionOrderAnswers() {
        return questionOrderAnswerService.getAllQuestionOrderAnswer();
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrderAnswer> getAllQuestionOrderAnswersByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionOrderAnswerService.getAllQuestionOrderAnswerByUser(jwt.getSub());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionOrderAnswer createQuestionOrderAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionOrderAnswerCreateDTO questionOrderAnswer) {
        return questionOrderAnswerService.createQuestionOrderAnswer(jwt.getSub(), questionOrderAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderAnswer updateQuestionOrderAnswer(@RequestBody QuestionOrderAnswerUpdateDTO questionOrderAnswer) {
        return questionOrderAnswerService.updateQuestionOrderAnswer(questionOrderAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderAnswer deleteQuestionOrderAnswer(@RequestParam UUID id) {
        return questionOrderAnswerService.deleteQuestionOrderAnswer(id);
    }
}
