package com.learningpulse.quiz.question.question_pair.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection.QuestionPairCollectionCreateDTO;
import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection.QuestionPairCollectionUpdateDTO;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_pair.service.QuestionPairCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/pair/collection")
@RequiredArgsConstructor
public class QuestionPairCollectionController {
    private final QuestionPairCollectionService questionPairCollectionService;

    @GetMapping(value = "/", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollection getQuestionPairCollectionById(@RequestParam UUID id) {
        return questionPairCollectionService.getQuestionPairCollectionById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollection> getAllQuestionPairCollectionsByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionPairCollectionService.getAllQuestionPairCollectionsByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollection> getAllQuestionPairCollections() {
        return questionPairCollectionService.getAllQuestionPairCollections();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionPairCollection createQuestionPairCollection(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionPairCollectionCreateDTO questionPairCollection) {
        return questionPairCollectionService.createQuestionPairCollection(jwt.getSub(), questionPairCollection);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollection updateQuestionPairCollection(@RequestBody QuestionPairCollectionUpdateDTO questionPairCollection) {
        return questionPairCollectionService.updateQuestionPairCollection(questionPairCollection);
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollection deleteQuestionPairCollection(@RequestParam UUID id) {
        return questionPairCollectionService.deleteQuestionPairCollection(id);
    }

}
