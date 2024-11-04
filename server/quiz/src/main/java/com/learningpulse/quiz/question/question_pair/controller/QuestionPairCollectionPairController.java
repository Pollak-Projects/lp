package com.learningpulse.quiz.question.question_pair.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import com.learningpulse.quiz.question.question_pair.service.QuestionPairCollectionPairService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/pair/collection/pair")
@RequiredArgsConstructor
public class QuestionPairCollectionPairController {
    private final QuestionPairCollectionPairService questionPairCollectionPairService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionPair getQuestionPairCollectionPairById(@RequestParam UUID id) {
        return questionPairCollectionPairService.getQuestionPairCollectionPairById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionPair> getAllQuestionPairCollectionPairsByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionPairCollectionPairService.getAllQuestionPairCollectionPairsByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionPair> getAllQuestionPairCollectionPairs() {
        return questionPairCollectionPairService.getAllQuestionPairCollectionPairs();
    }

    @GetMapping(value = "/collection", params = "questionPairCollection_id")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionPair> getAllQuestionPairCollectionPairsByQuestion(@RequestParam UUID questionPairCollection_id) {
        return questionPairCollectionPairService.getAllQuestionPairCollectionPairsByQuestion(questionPairCollection_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionPairCollectionPair createQuestionPairCollectionPair(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionPairCollectionPair questionPairCollectionPair) {
        return questionPairCollectionPairService.createQuestionPairCollectionPair(jwt.getSub(), questionPairCollectionPair);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionPair updateQuestionPairCollectionPair(@RequestBody QuestionPairCollectionPair questionPairCollectionPair) {
        return questionPairCollectionPairService.updateQuestionPairCollectionPair(questionPairCollectionPair);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionPair deleteQuestionPairCollectionPair(@RequestParam UUID id) {
        return questionPairCollectionPairService.deleteQuestionPairCollectionPair(id);
    }
}
