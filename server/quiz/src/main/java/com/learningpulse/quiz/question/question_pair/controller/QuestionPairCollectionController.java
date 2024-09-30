package com.learningpulse.quiz.question.question_pair.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.service.QuestionPairCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/pair/collection")
@RequiredArgsConstructor
public class QuestionPairCollectionController {
    // TODO: Implement this controller
    private final QuestionPairCollectionService questionPairCollectionService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionPairCollection> getQuestionPairCollectionById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionPairCollection>> getAllQuestionPairCollectionsByUser(@RequestBody UserDTO user) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionPairCollection> createQuestionPairCollection(@RequestBody QuestionPairCollection questionPairCollection) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionPairCollection> updateQuestionPairCollection(@RequestBody QuestionPairCollection questionPairCollection) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionPairCollection> deleteQuestionPairCollection(@RequestParam UUID id) {
        return null;
    }

}
