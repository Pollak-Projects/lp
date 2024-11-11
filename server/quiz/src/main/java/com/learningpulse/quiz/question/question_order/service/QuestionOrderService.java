package com.learningpulse.quiz.question.question_order.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_order.dto.question_order.QuestionOrderCreateDTO;
import com.learningpulse.quiz.question.question_order.dto.question_order.QuestionOrderUpdateDTO;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question.question_order.repository.QuestionOrderRepository;
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
public class QuestionOrderService {
    private final QuestionOrderRepository questionOrderRepository;

    public QuestionOrder getQuestionOrderById(UUID id) {
        return questionOrderRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrder not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionOrder> getAllQuestionOrdersByUser(UUID sub) {
        List<QuestionOrder> questionOrders = questionOrderRepository.findAllByCreatedBy(sub);
        if (questionOrders.isEmpty())
            throw new HttpStatusCodeException("QuestionOrder not found", HttpStatus.NOT_FOUND);
        return questionOrders;
    }

    public List<QuestionOrder> getAllQuestionOrders() {
        List<QuestionOrder> questionOrders = questionOrderRepository.findAll();
        if (questionOrders.isEmpty())
            throw new HttpStatusCodeException("QuestionOrder not found", HttpStatus.NOT_FOUND);
        return questionOrders;
    }

    public QuestionOrder createQuestionOrder(UUID sub, @NotNull QuestionOrderCreateDTO dto) {
        QuestionOrder questionOrder = QuestionOrder.builder()
                .createdBy(sub)
                .quiz(Quiz.builder().id(dto.quizId()).build())
                .title(dto.title())
                .build();

        questionOrder.setOptions(dto.options().stream().map(o -> QuestionOrderOptions.builder()
                .createdBy(sub)
                .questionOrder(questionOrder)
                .title(o.title())
                .place(o.place())
                .build()).toList());

        return questionOrderRepository.save(questionOrder);
    }

    @Transactional
    public QuestionOrder updateQuestionOrder(@NotNull QuestionOrderUpdateDTO dto) {
        return questionOrderRepository.findById(dto.questionOrderId())
                .map(q -> {
                    if (dto.quizId() != null)
                        q.setQuiz(Quiz.builder().id(dto.quizId()).build());
                    if (dto.title() != null)
                        q.setTitle(dto.title());
                    if (dto.options() != null)
                        q.setOptions(dto.options().stream().map(o -> QuestionOrderOptions.builder()
                                .id(o)
                                .build()).toList());
                    return questionOrderRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrder not found", HttpStatus.NOT_FOUND));
    }

    public QuestionOrder deleteQuestionOrder(UUID id) {
        return questionOrderRepository.findById(id)
                .map(q -> {
                    questionOrderRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrder not found", HttpStatus.NOT_FOUND));
    }
}
