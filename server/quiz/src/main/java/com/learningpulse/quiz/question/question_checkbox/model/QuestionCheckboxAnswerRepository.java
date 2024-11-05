package com.learningpulse.quiz.question.question_checkbox.model;

import com.learningpulse.quiz.question.question_radio.model.QuestionRadioAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionCheckboxAnswerRepository extends JpaRepository<QuestionCheckboxAnswer, UUID> {
    List<QuestionCheckboxAnswer> findAllByCreatedBy(UUID sub);
}
