package com.learningpulse.quiz.question.question_order.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.service.QuestionOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/order")
@RequiredArgsConstructor
public class QuestionOrderController {
    // TODO: Implement this controller
    private final QuestionOrderService questionOrderService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionOrder> getQuestionOrderById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionOrder>> getAllQuestionOrdersByUser(@RequestBody UserDTO user) {
        return null;
    }

    @PostMapping(value = "/", params = "questionOrderId")
    @ResponseBody
    public Mono<QuestionOrder> createQuestionOrder(@RequestBody QuestionOrder questionOrder) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionOrder> updateQuestionOrder(@RequestBody QuestionOrder questionOrder) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionOrder> deleteQuestionOrder(@RequestParam UUID id) {
        return null;
    }
}
