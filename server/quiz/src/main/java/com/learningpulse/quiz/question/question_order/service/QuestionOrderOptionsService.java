package com.learningpulse.quiz.question.question_order.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question.question_order.repository.QuestionOrderOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionOrderOptionsService {
    private final QuestionOrderOptionsRepository questionOrderOptionsRepository;

    public QuestionOrderOptions getQuestionOrderOptionsById(UUID id) {
        return questionOrderOptionsRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderOptions not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionOrderOptions> getAllQuestionOrderOptionsByUser(UUID sub) {
        List<QuestionOrderOptions> questionOrderOptions = questionOrderOptionsRepository.findAllByCreatedBy(sub);
        if (questionOrderOptions.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderOptions not found", HttpStatus.NOT_FOUND);
        return questionOrderOptions;
    }

    public List<QuestionOrderOptions> getAllQuestionOrderOptions() {
        List<QuestionOrderOptions> questionOrderOptions = questionOrderOptionsRepository.findAll();
        if (questionOrderOptions.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderOptions not found", HttpStatus.NOT_FOUND);
        return questionOrderOptions;
    }

    public QuestionOrderOptions createQuestionOrderOptions(UUID sub, @NotNull QuestionOrderOptions questionOrderOptions) {
        questionOrderOptions.setCreatedBy(sub);
        return questionOrderOptionsRepository.save(questionOrderOptions);
    }

    public QuestionOrderOptions updateQuestionOrderOptions(@NotNull QuestionOrderOptions questionOrderOptions) {
        return questionOrderOptionsRepository.findById(questionOrderOptions.getId())
                .map(q -> questionOrderOptionsRepository.save(questionOrderOptions))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderOptions not found", HttpStatus.NOT_FOUND));
    }

    public QuestionOrderOptions deleteQuestionOrderOptions(UUID id) {
        return questionOrderOptionsRepository.findById(id)
                .map(q -> {
                    questionOrderOptionsRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderOptions not found", HttpStatus.NOT_FOUND));

    }
}
