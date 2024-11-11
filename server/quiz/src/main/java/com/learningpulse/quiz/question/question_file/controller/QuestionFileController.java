package com.learningpulse.quiz.question.question_file.controller;

import com.learningpulse.quiz.external.UserDTO;
import com.learningpulse.quiz.question.question_file.dto.question_file.QuestionFileCreateDTO;
import com.learningpulse.quiz.question.question_file.dto.question_file.QuestionFileUpdateDTO;
import com.learningpulse.quiz.question.question_file.model.QuestionFile;
import com.learningpulse.quiz.question.question_file.service.QuestionFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quiz/question/file")
@RequiredArgsConstructor
public class QuestionFileController {
    // TODO: Implement this controller
    private final QuestionFileService questionFileService;

    @GetMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionFile> getQuestionFileById(@RequestParam UUID id) {
        return null;
    }

    @GetMapping("/all")
    @ResponseBody
    public Mono<Iterable<QuestionFile>> getAllQuestionFilesByUser(@RequestBody UserDTO user) {
        return null;
    }

    @PostMapping("/")
    @ResponseBody
    public Mono<QuestionFile> createQuestionFile(@RequestBody QuestionFileCreateDTO questionFile) {
        return null;
    }

    @PutMapping("/")
    @ResponseBody
    public Mono<QuestionFile> updateQuestionFile(@RequestBody QuestionFileUpdateDTO questionFile) {
        return null;
    }

    @DeleteMapping(value = "/", params = "id")
    @ResponseBody
    public Mono<QuestionFile> deleteQuestionFile(@RequestParam UUID id) {
        return null;
    }
}
