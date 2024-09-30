package com.learningpulse.quiz.question.question_order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionOrderRepository extends JpaRepository<QuestionOrder, UUID> {
}