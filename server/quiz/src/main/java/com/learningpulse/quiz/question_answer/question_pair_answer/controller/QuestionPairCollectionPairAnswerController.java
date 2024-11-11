package com.learningpulse.quiz.question_answer.question_pair_answer.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_answer.QuestionPairCollectionPairAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_answer.QuestionPairCollectionPairAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.service.QuestionPairCollectionPairAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/pair/collection/pair")
@RequiredArgsConstructor
public class QuestionPairCollectionPairAnswerController {
    private final QuestionPairCollectionPairAnswerService questionPairCollectionPairAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionPairAnswer getQuestionPairCollectionPairAnswerById(@RequestParam UUID id) {
        return questionPairCollectionPairAnswerService.getQuestionPairCollectionPairAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionPairAnswer> getAllQuestionPairCollectionPairAnswersByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionPairCollectionPairAnswerService.getAllQuestionPairCollectionPairAnswersByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionPairAnswer> getAllQuestionPairCollectionPairAnswers() {
        return questionPairCollectionPairAnswerService.getAllQuestionPairCollectionPairAnswers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionPairCollectionPairAnswer createQuestionPairCollectionPairAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionPairCollectionPairAnswerCreateDTO questionPairCollectionPairAnswer) {
        return questionPairCollectionPairAnswerService.createQuestionPairCollectionPairAnswer(jwt.getSub(), questionPairCollectionPairAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionPairAnswer updateQuestionPairCollectionPairAnswer(@RequestBody QuestionPairCollectionPairAnswerUpdateDTO questionPairCollectionPairAnswer) {
        return questionPairCollectionPairAnswerService.updateQuestionPairCollectionPairAnswer(questionPairCollectionPairAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionPairAnswer deleteQuestionPairCollectionPairAnswer(@RequestParam UUID id) {
        return questionPairCollectionPairAnswerService.deleteQuestionPairCollectionPairAnswer(id);
    }
}
