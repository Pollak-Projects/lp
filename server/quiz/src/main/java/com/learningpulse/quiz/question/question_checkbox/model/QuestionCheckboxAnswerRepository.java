package com.learningpulse.quiz.question.question_checkbox.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionCheckboxAnswerRepository extends JpaRepository<QuestionCheckboxAnswer, UUID> {
}
