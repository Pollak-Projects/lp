package com.learningpulse.quiz.question.question_checkbox.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionCheckboxService {
    private final QuestionCheckboxRepository questionCheckboxRepository;

    public QuestionCheckbox getQuestionCheckboxById(UUID id) {
        return questionCheckboxRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckbox not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionCheckbox> getAllQuestionCheckboxsByUser(UUID sub) {
        List<QuestionCheckbox> questionCheckboxs = questionCheckboxRepository.findAllByCreatedBy(sub);
        if (questionCheckboxs.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckbox not found", HttpStatus.NOT_FOUND);
        return questionCheckboxs;
    }

    public List<QuestionCheckbox> getAllQuestionCheckboxs() {
        List<QuestionCheckbox> questionCheckboxs = questionCheckboxRepository.findAll();
        if (questionCheckboxs.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckbox not found", HttpStatus.NOT_FOUND);
        return questionCheckboxs;
    }

    public QuestionCheckbox createQuestionCheckbox(UUID sub, @NotNull QuestionCheckbox questionCheckbox) {
        questionCheckbox.setCreatedBy(sub);
        return questionCheckboxRepository.save(questionCheckbox);
    }

    public QuestionCheckbox updateQuestionCheckbox(@NotNull QuestionCheckbox questionCheckbox) {
        return questionCheckboxRepository.findById(questionCheckbox.getId())
                .map(q -> questionCheckboxRepository.save(questionCheckbox))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckbox not found", HttpStatus.NOT_FOUND));
    }

    public QuestionCheckbox deleteQuestionCheckbox(UUID id) {
        return questionCheckboxRepository.findById(id)
                .map(q -> {
                    questionCheckboxRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckbox not found", HttpStatus.NOT_FOUND));
    }
}
