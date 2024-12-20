package com.learningpulse.quiz.question.question_order.repository;

import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionOrderRepository extends JpaRepository<QuestionOrder, UUID> {
    List<QuestionOrder> findAllByCreatedBy(UUID sub);
}
