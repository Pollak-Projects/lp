package com.learningpulse.quiz.question.question_order.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.repository.QuestionOrderRepository;
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

    public QuestionOrder createQuestionOrder(UUID sub, @NotNull QuestionOrder questionOrder) {
        questionOrder.setCreatedBy(sub);
        return questionOrderRepository.save(questionOrder);
    }

    public QuestionOrder updateQuestionOrder(@NotNull QuestionOrder questionOrder) {
        return questionOrderRepository.findById(questionOrder.getId())
                .map(q -> questionOrderRepository.save(questionOrder))
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
