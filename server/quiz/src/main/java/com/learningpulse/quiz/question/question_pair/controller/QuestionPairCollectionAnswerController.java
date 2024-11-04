package com.learningpulse.quiz.question.question_pair.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.question.question_pair.service.QuestionPairCollectionAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/pair/collection")
@RequiredArgsConstructor
public class QuestionPairCollectionAnswerController {
    private final QuestionPairCollectionAnswerService questionPairCollectionAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionAnswer getQuestionPairCollectionAnswerById(@RequestParam UUID id) {
        return questionPairCollectionAnswerService.getQuestionPairCollectionAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswersByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionPairCollectionAnswerService.getAllQuestionPairCollectionAnswersByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswers() {
        return questionPairCollectionAnswerService.getAllQuestionPairCollectionAnswers();
    }

    @GetMapping(value = "/collection", params = "questionPairCollection_id")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionPairCollectionAnswer> getAllQuestionPairCollectionAnswersByQuestionPairCollectionId(@RequestParam UUID questionPairCollection_id) {
        return questionPairCollectionAnswerService.getAllQuestionPairCollectionAnswersByQuestionPairCollectionId(questionPairCollection_id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionPairCollectionAnswer createQuestionPairCollectionAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionPairCollectionAnswer questionPairCollectionAnswer) {
        return questionPairCollectionAnswerService.createQuestionPairCollectionAnswer(jwt.getSub(), questionPairCollectionAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionAnswer updateQuestionPairCollectionAnswer(@RequestBody QuestionPairCollectionAnswer questionPairCollectionAnswer) {
        return questionPairCollectionAnswerService.updateQuestionPairCollectionAnswer(questionPairCollectionAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPairCollectionAnswer deleteQuestionPairCollectionAnswer(@RequestParam UUID id) {
        return questionPairCollectionAnswerService.deleteQuestionPairCollectionAnswer(id);
    }
}
