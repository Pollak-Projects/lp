package com.learningpulse.quiz.question.question_pair.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question.question_pair.service.QuestionPairCollectionPairService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/pair/collection/pair")
@RequiredArgsConstructor
public class QuestionPairCollectionPairController {
    // TODO: Implement this controller
    private final QuestionPairCollectionPairService questionPairCollectionPairService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionPairCollectionPair> getQuestionPairCollectionPairById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionPairCollectionPair>> getAllQuestionPairCollectionPairsByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping(value = "/", params = "questionPairId")
    @ResponseBody
    public Mono<Iterable<QuestionPairCollectionPair>> getAllQuestionPairCollectionPairsByQuestion(@RequestParam UUID questionPairId) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionPairCollectionPair> createQuestionPairCollectionPair(@RequestBody QuestionPairCollectionPair questionPairCollectionPair) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionPairCollectionPair> updateQuestionPairCollectionPair(@RequestBody QuestionPairCollectionPair questionPairCollectionPair) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionPairCollectionPair> deleteQuestionPairCollectionPair(@RequestParam UUID id) {
        return null;
    }
}
