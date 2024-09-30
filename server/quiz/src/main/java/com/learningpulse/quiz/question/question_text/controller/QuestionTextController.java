package com.learningpulse.quiz.question.question_text.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.question.question_text.service.QuestionTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/text")
@RequiredArgsConstructor
public class QuestionTextController {
    // TODO: Implement this controller
    private final QuestionTextService questionTextService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionText> getQuestionTextById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionText>> getAllQuestionTextsByUser(@RequestBody UserDTO user) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionText> createQuestionText(@RequestBody QuestionText questionText) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionText> updateQuestionText(@RequestBody QuestionText questionText) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionText> deleteQuestionText(@RequestParam UUID id) {
        return null;
    }
}
