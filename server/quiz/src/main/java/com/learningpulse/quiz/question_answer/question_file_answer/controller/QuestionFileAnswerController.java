package com.learningpulse.quiz.question_answer.question_file_answer.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question_answer.question_file_answer.model.QuestionFileAnswer;
import com.learningpulse.quiz.question_answer.question_file_answer.service.QuestionFileAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/file")
@RequiredArgsConstructor
public class QuestionFileAnswerController {
    // TODO: Implement this controller
    private final QuestionFileAnswerService questionFileAnswerService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionFileAnswer> getQuestionFileAnswerById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionFileAnswer>> getAllQuestionFileAnswersByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping(value = "/", params = "questionFileId")
    @ResponseBody
    public Mono<Iterable<QuestionFileAnswer>> getAllQuestionFileAnswersByQuestion(@RequestParam UUID questionFileId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionFileAnswer> createQuestionFileAnswer(@RequestBody QuestionFileAnswer questionFileAnswer) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionFileAnswer> updateQuestionFileAnswer(@RequestBody QuestionFileAnswer questionFileAnswer) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionFileAnswer> deleteQuestionFileAnswer(@RequestParam UUID id) {
        return null;
    }
}
