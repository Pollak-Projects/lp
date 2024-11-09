package com.learningpulse.quiz.question.question_order.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.service.QuestionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/order")
@RequiredArgsConstructor
public class QuestionOrderController {
    private final QuestionOrderService questionOrderService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrder getQuestionOrderById(@RequestBody UUID id) {
        return questionOrderService.getQuestionOrderById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrder> getAllQuestionOrdersByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionOrderService.getAllQuestionOrdersByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrder> getAllQuestionOrders() {
        return questionOrderService.getAllQuestionOrders();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionOrder createQuestionOrder(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionOrder questionOrder) {
        return questionOrderService.createQuestionOrder(jwt.getSub(), questionOrder);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrder updateQuestionOrder(@RequestBody QuestionOrder questionOrder) {
        return questionOrderService.updateQuestionOrder(questionOrder);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrder deleteQuestionOrder(@RequestBody UUID id) {
        return questionOrderService.deleteQuestionOrder(id);
    }
}
