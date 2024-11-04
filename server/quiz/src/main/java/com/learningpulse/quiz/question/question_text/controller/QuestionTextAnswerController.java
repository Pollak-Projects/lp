package com.learningpulse.quiz.question.question_text.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_text.model.QuestionTextAnswer;
import com.learningpulse.quiz.question.question_text.service.QuestionTextAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/text")
@RequiredArgsConstructor
public class QuestionTextAnswerController {
    private final QuestionTextAnswerService questionTextAnswerService;

    @GetMapping(value = "/", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionTextAnswer getQuestionTextAnswerById(@RequestParam UUID id) {
        return questionTextAnswerService.getQuestionTextAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionTextAnswer> getAllQuestionTextAnswersByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionTextAnswerService.getAllQuestionTextAnswersByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionTextAnswer> getAllQuestionTextAnswers() {
        return questionTextAnswerService.getAllQuestionTextAnswers();
    }

    @GetMapping(value = "/all", params = "questionTextId")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionTextAnswer> getAllQuestionTextAnswersByQuestion(@RequestParam UUID questionTextId) {
        return questionTextAnswerService.getAllQuestionTextAnswersByQuestion(questionTextId);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionTextAnswer createQuestionTextAnswer(@RequestBody QuestionTextAnswer questionTextAnswer) {
        return questionTextAnswerService.createQuestionTextAnswer(questionTextAnswer);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public QuestionTextAnswer updateQuestionTextAnswer(@RequestBody QuestionTextAnswer questionTextAnswer) {
        return questionTextAnswerService.updateQuestionTextAnswer(questionTextAnswer);
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionTextAnswer deleteQuestionTextAnswer(@RequestParam UUID id) {
        return questionTextAnswerService.deleteQuestionTextAnswer(id);
    }

}
