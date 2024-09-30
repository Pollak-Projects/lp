package com.learningpulse.quiz.question.question_pair.controller;

import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.question.question_pair.service.QuestionPairCollectionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/pair/collection")
@RequiredArgsConstructor
public class QuestionPairCollectionAnswerController {
    // TODO: Implement this controller
    private final QuestionPairCollectionAnswerService questionPairCollectionAnswerService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionPairCollectionAnswer> getQuestionPairCollectionAnswerById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionPairCollectionAnswer>> getAllQuestionPairCollectionAnswersByUser(@RequestBody UUID user) {
        return null;
    }

    @GetMapping(value = "/", params = "questionPairCollectionId")
    @ResponseBody
    public Mono<Iterable<QuestionPairCollectionAnswer>> getAllQuestionPairCollectionAnswersByQuestion(@RequestParam UUID questionPairCollectionId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionPairCollectionAnswer> createQuestionPairCollectionAnswer(@RequestBody QuestionPairCollectionAnswer questionPairCollectionAnswer) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionPairCollectionAnswer> updateQuestionPairCollectionAnswer(@RequestBody QuestionPairCollectionAnswer questionPairCollectionAnswer) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionPairCollectionAnswer> deleteQuestionPairCollectionAnswer(@RequestParam UUID id) {
        return null;
    }
}
