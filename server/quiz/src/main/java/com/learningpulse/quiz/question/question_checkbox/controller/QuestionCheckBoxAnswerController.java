package com.learningpulse.quiz.question.question_checkbox.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxAnswer;
import com.learningpulse.quiz.question.question_checkbox.service.QuestionCheckboxAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/checkbox")
@RequiredArgsConstructor
public class QuestionCheckBoxAnswerController {
    // TODO: Implement this controller
    private final QuestionCheckboxAnswerService questionCheckboxAnswerService;

    @GetMapping(name = "/", params = "id")
    @ResponseBody
    public Mono<QuestionCheckboxAnswer> getQuestionCheckboxAnswerById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionCheckboxAnswer>> getAllQuestionCheckboxAnswersByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping(name = "/", params = "questionCheckboxId")
    @ResponseBody
    public Mono<Iterable<QuestionCheckboxAnswer>> getAllQuestionCheckboxAnswersByQuestion(@RequestParam UUID questionCheckboxId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionCheckboxAnswer> createQuestionCheckboxAnswer(@RequestBody QuestionCheckboxAnswer questionCheckboxAnswer) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionCheckboxAnswer> updateQuestionCheckboxAnswer(@RequestBody QuestionCheckboxAnswer questionCheckboxAnswer) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionCheckboxAnswer> deleteQuestionCheckboxAnswer(@RequestParam UUID id) {
        return null;
    }
}
