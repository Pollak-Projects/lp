package com.learningpulse.quiz.question_answer.question_order_answer.repository;

import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderOptionsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionOrderOptionsAnswerRepository extends JpaRepository<QuestionOrderOptionsAnswer, UUID> {
    List<QuestionOrderOptionsAnswer> findAllByCreatedBy(UUID sub);
}
