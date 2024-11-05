package com.learningpulse.quiz.question.question_checkbox.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionCheckboxOptionsService {
    private final QuestionCheckboxOptionsRepository questionCheckboxOptionsRepository;

    public QuestionCheckboxOptions getQuestionCheckboxOptionsById(UUID id) {
        return questionCheckboxOptionsRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxOptions not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionCheckboxOptions> getAllQuestionCheckboxOptionsByUser(UUID sub) {
        List<QuestionCheckboxOptions> questionCheckboxOptions = questionCheckboxOptionsRepository.findAllByCreatedBy(sub);
        if (questionCheckboxOptions.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxOptions not found", HttpStatus.NOT_FOUND);
        return questionCheckboxOptions;
    }

    public List<QuestionCheckboxOptions> getAllQuestionCheckboxOptions() {
        List<QuestionCheckboxOptions> questionCheckboxOptions = questionCheckboxOptionsRepository.findAll();
        if (questionCheckboxOptions.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxOptions not found", HttpStatus.NOT_FOUND);
        return questionCheckboxOptions;
    }

    public QuestionCheckboxOptions createQuestionCheckboxOptions(UUID sub, @NotNull QuestionCheckboxOptions questionCheckboxOptions) {
        questionCheckboxOptions.setCreatedBy(sub);
        return questionCheckboxOptionsRepository.save(questionCheckboxOptions);
    }

    public QuestionCheckboxOptions updateQuestionCheckboxOptions(@NotNull QuestionCheckboxOptions questionCheckboxOptions) {
        return questionCheckboxOptionsRepository.findById(questionCheckboxOptions.getId())
                .map(q -> questionCheckboxOptionsRepository.save(questionCheckboxOptions))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxOptions not found", HttpStatus.NOT_FOUND));
    }

    public QuestionCheckboxOptions deleteQuestionCheckboxOptions(UUID id) {
        return questionCheckboxOptionsRepository.findById(id)
                .map(q -> {
                    questionCheckboxOptionsRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxOptions not found", HttpStatus.NOT_FOUND));

    }
}
