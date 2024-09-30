package com.learningpulse.quiz.question.question_radio.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRadioOptionsRepository extends JpaRepository<QuestionRadioOptions, UUID> {
}
