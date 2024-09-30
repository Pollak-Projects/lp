package com.learningpulse.quiz.question.question_order.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionOrderAnswerRepository extends JpaRepository<QuestionOrderAnswer, UUID> {
}
