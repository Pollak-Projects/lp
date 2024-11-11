package com.learningpulse.quiz.question_answer.question_order_answer.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_options_answer.QuestionOrderOptionsAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_options_answer.QuestionOrderOptionsAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.service.QuestionOrderOptionsAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/order/options")
@RequiredArgsConstructor
public class QuestionOrderOptionsAnswerController {
    private final QuestionOrderOptionsAnswerService questionOrderOptionsAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderOptionsAnswer getQuestionOrderOptionsAnswerById(@RequestParam UUID id) {
        return questionOrderOptionsAnswerService.getQuestionOrderOptionsAnswerById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrderOptionsAnswer> getAllQuestionOrderOptionsAnswers() {
        return questionOrderOptionsAnswerService.getAllQuestionOrderOptionsAnswer();
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionOrderOptionsAnswer> getAllQuestionOrderOptionsAnswersByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionOrderOptionsAnswerService.getAllQuestionOrderOptionsAnswerByUser(jwt.getSub());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionOrderOptionsAnswer createQuestionOrderOptionsAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionOrderOptionsAnswerCreateDTO questionOrderOptionsAnswer) {
        return questionOrderOptionsAnswerService.createQuestionOrderOptionsAnswer(jwt.getSub(), questionOrderOptionsAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderOptionsAnswer updateQuestionOrderOptionsAnswer(@RequestBody QuestionOrderOptionsAnswerUpdateDTO questionOrderOptionsAnswer) {
        return questionOrderOptionsAnswerService.updateQuestionOrderOptionsAnswer(questionOrderOptionsAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionOrderOptionsAnswer deleteQuestionOrderOptionsAnswer(@RequestParam UUID id) {
        return questionOrderOptionsAnswerService.deleteQuestionOrderOptionsAnswer(id);
    }
}
