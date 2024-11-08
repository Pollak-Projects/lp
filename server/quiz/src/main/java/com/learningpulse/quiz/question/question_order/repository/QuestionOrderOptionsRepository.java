package com.learningpulse.quiz.question.question_order.repository;

import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionOrderOptionsRepository extends JpaRepository<QuestionOrderOptions, UUID> {
    List<QuestionOrderOptions> findAllByCreatedBy(UUID sub);
}
