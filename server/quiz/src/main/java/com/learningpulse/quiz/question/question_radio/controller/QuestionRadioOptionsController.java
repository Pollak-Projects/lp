package com.learningpulse.quiz.question.question_radio.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question.question_radio.service.QuestionRadioOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/radio/options")
@RequiredArgsConstructor
public class QuestionRadioOptionsController {
    // TODO: Implement this controller
    private final QuestionRadioOptionsService questionRadioOptionsService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionRadioOptions> getQuestionRadioOptionsById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionRadioOptions>> getAllQuestionRadioOptionsByUser(@RequestBody UserDTO user) {
        return null;
    }

    @GetMapping(value = "/", params = "questionRadioId")
    @ResponseBody
    public Mono<Iterable<QuestionRadioOptions>> getAllQuestionRadioOptionsByQuestion(@RequestParam UUID questionRadioId) {
        return null;
    }
}
