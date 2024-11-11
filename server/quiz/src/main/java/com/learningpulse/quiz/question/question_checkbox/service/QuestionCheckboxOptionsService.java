package com.learningpulse.quiz.question.question_checkbox.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options.QuestionCheckboxOptionsCreateDTO;
import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options.QuestionCheckboxOptionsUpdateDTO;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question.question_checkbox.repository.QuestionCheckboxOptionsRepository;
import jakarta.transaction.Transactional;
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

    public QuestionCheckboxOptions createQuestionCheckboxOptions(UUID sub, @NotNull QuestionCheckboxOptionsCreateDTO questionCheckboxOptionsCreateDTO) {
        QuestionCheckboxOptions questionCheckboxOptions = QuestionCheckboxOptions.builder()
                .createdBy(sub)
                .questionCheckbox(QuestionCheckbox.builder().id(questionCheckboxOptionsCreateDTO.questionCheckboxId()).build())
                .name(questionCheckboxOptionsCreateDTO.name())
                .answer(questionCheckboxOptionsCreateDTO.answer())
                .build();
        return questionCheckboxOptionsRepository.save(questionCheckboxOptions);
    }

    @Transactional
    public QuestionCheckboxOptions updateQuestionCheckboxOptions(@NotNull QuestionCheckboxOptionsUpdateDTO dto) {
        return questionCheckboxOptionsRepository.findById(dto.questionCheckboxOptionId())
                .map(q -> {
                    if (dto.questionCheckboxId() != null)
                        q.setQuestionCheckbox(QuestionCheckbox.builder().id(dto.questionCheckboxId()).build());
                    if (dto.name() != null)
                        q.setName(dto.name());
                    if (dto.answer() != null)
                        q.setAnswer(dto.answer());
                    return questionCheckboxOptionsRepository.save(q);
                })
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
