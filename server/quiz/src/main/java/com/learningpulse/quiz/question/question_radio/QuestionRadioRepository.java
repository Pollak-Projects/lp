package com.learningpulse.quiz.question.question_radio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRadioRepository extends JpaRepository<QuestionRadio, UUID> {
}