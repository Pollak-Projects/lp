package com.learningpulse.quiz.quiz_answer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuizAnswerRepository extends JpaRepository<QuizAnswer, UUID> {
    QuizAnswer findAllByCreatedBy(UUID sub);
}
