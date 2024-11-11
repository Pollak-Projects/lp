package com.learningpulse.quiz.question.question_checkbox.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox.QuestionCheckboxCreateDTO;
import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox.QuestionCheckboxUpdateDTO;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question.question_checkbox.repository.QuestionCheckboxRepository;
import com.learningpulse.quiz.quiz.Quiz;
import jakarta.transaction.Transactional;
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

    public List<QuestionCheckbox> getAllQuestionCheckboxesByUser(UUID sub) {
        List<QuestionCheckbox> questionCheckboxes = questionCheckboxRepository.findAllByCreatedBy(sub);
        if (questionCheckboxes.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckbox not found", HttpStatus.NOT_FOUND);
        return questionCheckboxes;
    }

    public List<QuestionCheckbox> getAllQuestionCheckboxes() {
        List<QuestionCheckbox> questionCheckboxes = questionCheckboxRepository.findAll();
        if (questionCheckboxes.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckbox not found", HttpStatus.NOT_FOUND);
        return questionCheckboxes;
    }

    public QuestionCheckbox createQuestionCheckbox(UUID sub, @NotNull QuestionCheckboxCreateDTO dto) {
        QuestionCheckbox questionCheckbox = QuestionCheckbox.builder()
                .createdBy(sub)
                .quiz(Quiz.builder().id(dto.quizId()).build())
                .title(dto.title())
                .build();

        questionCheckbox.setOptions(dto.options().stream()
                .map(o -> QuestionCheckboxOptions.builder()
                        .createdBy(sub)
                        .questionCheckbox(questionCheckbox)
                        .name(o.name())
                        .answer(o.answer())
                        .build())
                .toList());
        return questionCheckboxRepository.save(questionCheckbox);
    }

    @Transactional
    public QuestionCheckbox updateQuestionCheckbox(@NotNull QuestionCheckboxUpdateDTO dto) {
        return questionCheckboxRepository.findById(dto.questionCheckboxId())
                .map(q -> {
                    if (dto.quizId() != null)
                        q.setQuiz(Quiz.builder().id(dto.quizId()).build());
                    if (dto.title() != null)
                        q.setTitle(dto.title());
                    if (dto.options() != null && !dto.options().isEmpty())
                        q.setOptions(dto.options().stream().map(o -> QuestionCheckboxOptions.builder()
                                .id(o)
                                .build()).toList());
                    return questionCheckboxRepository.save(q);
                })
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
