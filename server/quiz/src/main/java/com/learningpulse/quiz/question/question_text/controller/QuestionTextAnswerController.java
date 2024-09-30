package com.learningpulse.quiz.question.question_text.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_text.model.QuestionTextAnswer;
import com.learningpulse.quiz.question.question_text.service.QuestionTextAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/text")
@RequiredArgsConstructor
public class QuestionTextAnswerController {
    // TODO: Implement this controller
    private final QuestionTextAnswerService questionTextAnswerService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionTextAnswer> getQuestionTextAnswerById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionTextAnswer>> getAllQuestionTextAnswersByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping(value = "/all", params = "questionTextId")
    @ResponseBody
    public Mono<Iterable<QuestionTextAnswer>> getAllQuestionTextAnswersByQuestion(@RequestParam UUID questionTextId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionTextAnswer> createQuestionTextAnswer(@RequestBody QuestionTextAnswer questionTextAnswer) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionTextAnswer> updateQuestionTextAnswer(@RequestBody QuestionTextAnswer questionTextAnswer) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionTextAnswer> deleteQuestionTextAnswer(@RequestParam UUID id) {
        return null;
    }

}
