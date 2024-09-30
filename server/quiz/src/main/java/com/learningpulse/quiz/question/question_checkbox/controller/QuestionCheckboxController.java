package com.learningpulse.quiz.question.question_checkbox.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.service.QuestionCheckboxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/checkbox")
@RequiredArgsConstructor
public class QuestionCheckboxController {
    // TODO: Implement this controller
    private final QuestionCheckboxService questionCheckboxService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionCheckbox> getQuestionCheckboxById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionCheckbox>> getAllQuestionCheckboxesByUser(@RequestBody UserDTO user) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionCheckbox> createQuestionCheckbox(@RequestBody QuestionCheckbox questionCheckbox) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionCheckbox> updateQuestionCheckbox(@RequestBody QuestionCheckbox questionCheckbox) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionCheckbox> deleteQuestionCheckbox(@RequestParam UUID id) {
        return null;
    }
}
