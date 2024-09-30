package com.learningpulse.quiz.question.question_radio.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.service.QuestionRadioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/radio")
@RequiredArgsConstructor
public class QuestionRadioController {
    // TODO: Implement this controller
    private final QuestionRadioService questionRadioService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionRadio> getQuestionRadioById(@RequestBody UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionRadio>> getAllQuestionRadiosByUser(@RequestBody UserDTO user) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionRadio> createQuestionRadio(@RequestBody QuestionRadio questionRadio) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionRadio> updateQuestionRadio(@RequestBody QuestionRadio questionRadio) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionRadio> deleteQuestionRadio(@RequestParam UUID id) {
        return null;
    }
}
