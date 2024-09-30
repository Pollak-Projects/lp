package com.learningpulse.quiz.question.question_checkbox.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question.question_checkbox.service.QuestionCheckboxOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/checkbox/options")
@RequiredArgsConstructor
public class QuestionCheckboxOptionsController {
    // TODO: Implement this controller
    private final QuestionCheckboxOptionsService questionCheckboxOptionsService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionCheckboxOptions> getQuestionCheckboxOptionsById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionCheckboxOptions>> getAllQuestionCheckboxOptionsByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping(value = "/", params = "questionCheckboxId")
    @ResponseBody
    public Mono<Iterable<QuestionCheckboxOptions>> getAllQuestionCheckboxOptionsByQuestion(@RequestParam UUID questionCheckboxId) {
        return null;
    }

    @GetMapping(value = "/", params = "questionCheckboxAnswerId")
    @ResponseBody
    public Mono<Iterable<QuestionCheckboxOptions>> getAllQuestionCheckboxOptionsByQuestionAnswer(@RequestParam UUID questionCheckboxAnswerId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionCheckboxOptions> createQuestionCheckboxOptions(@RequestBody QuestionCheckboxOptions questionCheckboxOptions) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionCheckboxOptions> updateQuestionCheckboxOptions(@RequestBody QuestionCheckboxOptions questionCheckboxOptions) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionCheckboxOptions> deleteQuestionCheckboxOptions(@RequestParam UUID id) {
        return null;
    }
}
