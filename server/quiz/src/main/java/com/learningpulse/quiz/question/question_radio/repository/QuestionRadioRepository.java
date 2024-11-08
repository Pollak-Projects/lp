package com.learningpulse.quiz.question.question_radio.repository;

import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRadioRepository extends JpaRepository<QuestionRadio, UUID> {
    List<QuestionRadio> findAllByCreatedBy(UUID sub);
}
