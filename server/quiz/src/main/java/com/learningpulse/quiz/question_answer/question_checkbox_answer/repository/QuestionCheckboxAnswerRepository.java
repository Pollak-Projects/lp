package com.learningpulse.quiz.question_answer.question_checkbox_answer.repository;

import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionCheckboxAnswerRepository extends JpaRepository<QuestionCheckboxAnswer, UUID> {
    List<QuestionCheckboxAnswer> findAllByCreatedBy(UUID sub);
}
