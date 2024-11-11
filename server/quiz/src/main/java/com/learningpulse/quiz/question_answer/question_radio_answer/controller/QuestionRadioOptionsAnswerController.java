package com.learningpulse.quiz.question_answer.question_radio_answer.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_options_answer.QuestionRadioOptionsAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_options_answer.QuestionRadioOptionsAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.service.QuestionRadioOptionsAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/answer/radio/options")
@RequiredArgsConstructor
public class QuestionRadioOptionsAnswerController {
    private final QuestionRadioOptionsAnswerService questionRadioOptionsAnswerService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioOptionsAnswer getQuestionRadioOptionsAnswerById(@RequestParam UUID id) {
        return questionRadioOptionsAnswerService.getQuestionRadioOptionsAnswerById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadioOptionsAnswer> getAllQuestionRadioOptionsAnswerByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionRadioOptionsAnswerService.getAllQuestionRadioOptionsAnswerByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionRadioOptionsAnswer> getAllQuestionRadioOptionsAnswer() {
        return questionRadioOptionsAnswerService.getAllQuestionRadioOptionsAnswer();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionRadioOptionsAnswer createQuestionRadioOptionsAnswer(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionRadioOptionsAnswerCreateDTO questionRadioOptionsAnswer) {
        return questionRadioOptionsAnswerService.createQuestionRadioOptionsAnswer(jwt.getSub(), questionRadioOptionsAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioOptionsAnswer updateQuestionRadioOptionsAnswer(@RequestBody QuestionRadioOptionsAnswerUpdateDTO questionRadioOptionsAnswer) {
        return questionRadioOptionsAnswerService.updateQuestionRadioOptionsAnswer(questionRadioOptionsAnswer);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionRadioOptionsAnswer deleteQuestionRadioOptionsAnswer(@RequestParam UUID id) {
        return questionRadioOptionsAnswerService.deleteQuestionRadioOptionsAnswer(id);
    }
}
