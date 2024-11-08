package com.learningpulse.quiz.question_answer.question_radio_answer.repository;

import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRadioAnswerRepository extends JpaRepository<QuestionRadioAnswer, UUID> {
    List<QuestionRadioAnswer> findAllByCreatedBy(UUID sub);
}
