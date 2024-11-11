package com.learningpulse.quiz.question.question_order.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_order.dto.question_order_options.QuestionOrderOptionsCreateDTO;
import com.learningpulse.quiz.question.question_order.dto.question_order_options.QuestionOrderOptionsUpdateDTO;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question.question_order.repository.QuestionOrderOptionsRepository;
import jakarta.transaction.Transactional;
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

    public QuestionOrderOptions createQuestionOrderOptions(UUID sub, @NotNull QuestionOrderOptionsCreateDTO dto) {
        QuestionOrderOptions questionOrderOptions = QuestionOrderOptions.builder()
                .createdBy(sub)
                .questionOrder(QuestionOrder.builder().id(dto.questionOrderId()).build())
                .title(dto.title())
                .place(dto.place())
                .build();
        return questionOrderOptionsRepository.save(questionOrderOptions);
    }

    @Transactional
    public QuestionOrderOptions updateQuestionOrderOptions(@NotNull QuestionOrderOptionsUpdateDTO dto) {
        return questionOrderOptionsRepository.findById(dto.questionOrderOptionsId())
                .map(q -> {
                    if (dto.questionOrderId() != null)
                        q.setQuestionOrder(QuestionOrder.builder().id(dto.questionOrderId()).build());
                    if (dto.title() != null)
                        q.setTitle(dto.title());
                    if (dto.place() != null)
                        q.setPlace(dto.place());
                    return questionOrderOptionsRepository.save(q);
                })
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
