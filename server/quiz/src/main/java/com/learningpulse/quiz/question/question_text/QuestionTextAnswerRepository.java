package com.learningpulse.quiz.question.question_text;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionTextAnswerRepository extends JpaRepository<QuestionTextAnswer, UUID> {
}
