package com.learningpulse.quiz.question_answer.question_file_answer.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionFileAnswerRepository extends JpaRepository<QuestionFileAnswer, UUID> {
}
