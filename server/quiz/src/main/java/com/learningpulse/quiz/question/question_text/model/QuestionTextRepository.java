package com.learningpulse.quiz.question.question_text.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionTextRepository extends JpaRepository<QuestionText, UUID> {
}