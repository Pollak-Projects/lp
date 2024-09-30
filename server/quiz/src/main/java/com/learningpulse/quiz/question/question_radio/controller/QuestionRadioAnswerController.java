package com.learningpulse.quiz.question.question_radio.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioAnswer;
import com.learningpulse.quiz.question.question_radio.service.QuestionRadioAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/radio")
@RequiredArgsConstructor
public class QuestionRadioAnswerController {
    // TODO: Implement this controller
    private final QuestionRadioAnswerService questionRadioAnswerService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionRadioAnswer> getQuestionRadioAnswerById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionRadioAnswer>> getAllQuestionRadioAnswersByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping(value = "/", params = "questionRadioId")
    @ResponseBody
    public Mono<Iterable<QuestionRadioAnswer>> getAllQuestionRadioAnswersByQuestion(@RequestParam UUID questionRadioId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionRadioAnswer> createQuestionRadioAnswer(@RequestBody QuestionRadioAnswer questionRadioAnswer) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionRadioAnswer> updateQuestionRadioAnswer(@RequestBody QuestionRadioAnswer questionRadioAnswer) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionRadioAnswer> deleteQuestionRadioAnswer(@RequestParam UUID id) {
        return null;
    }
}
