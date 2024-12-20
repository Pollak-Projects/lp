package com.learningpulse.quiz.question.question_text.controller;

import com.learningpulse.quiz.config.KeycloakJwt;
import com.learningpulse.quiz.question.question_text.dto.question_text.QuestionTextCreateDTO;
import com.learningpulse.quiz.question.question_text.dto.question_text.QuestionTextUpdateDTO;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.question.question_text.service.QuestionTextService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/text")
@RequiredArgsConstructor
public class QuestionTextController {
    private static final Logger logger = LoggerFactory.getLogger(QuestionTextController.class);
    // IMPORTANT: For these to work QuestionText.id must be null
    private final QuestionTextService questionTextService;

    @GetMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionText getQuestionTextById(@RequestParam UUID id) {
        return questionTextService.getQuestionTextById(id);
    }

    @GetMapping("/current-user")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionText> getAllQuestionTextsByUser(@AuthenticationPrincipal KeycloakJwt jwt) {
        return questionTextService.getAllQuestionTextsByUser(jwt.getSub());
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionText> getAllQuestionTexts() {
        return questionTextService.getAllQuestionTexts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionText createQuestionText(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody QuestionTextCreateDTO questionTextCreateDTO) {
        logger.atDebug().log("Creating question text", questionTextCreateDTO);
        return questionTextService.createQuestionText(jwt.getSub(), questionTextCreateDTO);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public QuestionText updateQuestionText(@RequestBody QuestionTextUpdateDTO questionText) {
        return questionTextService.updateQuestionText(questionText);
    }

    @DeleteMapping(params = "id")
    @ResponseStatus(HttpStatus.OK)
    public QuestionText deleteQuestionText(@RequestParam UUID id) {
        return questionTextService.deleteQuestionText(id);
    }
}
