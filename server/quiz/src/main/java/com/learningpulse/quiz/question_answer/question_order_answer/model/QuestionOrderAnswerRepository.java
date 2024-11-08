package com.learningpulse.quiz.question_answer.question_order_answer.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionOrderAnswerRepository extends JpaRepository<QuestionOrderAnswer, UUID> {
    List<QuestionOrderAnswer> findAllByCreatedBy(UUID sub);
}
