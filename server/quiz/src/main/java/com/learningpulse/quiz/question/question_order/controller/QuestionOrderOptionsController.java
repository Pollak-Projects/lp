package com.learningpulse.quiz.question.question_order.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question.question_order.service.QuestionOrderOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/order/options")
@RequiredArgsConstructor
public class QuestionOrderOptionsController {
    // TODO: Implement this controller
    private final QuestionOrderOptionsService questionOrderOptionsService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionOrderOptions> getQuestionOrderOptionsById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionOrderOptions>> getAllQuestionOrderOptionsByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping("/")
    @ResponseBody
    public Mono<Iterable<QuestionOrderOptions>> getAllQuestionOrderOptionsByQuestion(@RequestParam UUID questionOrderOptionsId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionOrderOptions> createQuestionOrderOptions(@RequestBody QuestionOrderOptions questionOrderOptions) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionOrderOptions> updateQuestionOrderOptions(@RequestBody QuestionOrderOptions questionOrderOptions) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionOrderOptions> deleteQuestionOrderOptions(@RequestParam UUID id) {
        return null;
    }
}
