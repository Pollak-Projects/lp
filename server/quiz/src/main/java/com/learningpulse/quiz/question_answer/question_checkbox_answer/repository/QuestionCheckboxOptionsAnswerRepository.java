package com.learningpulse.quiz.question_answer.question_checkbox_answer.repository;

import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxOptionsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionCheckboxOptionsAnswerRepository extends JpaRepository<QuestionCheckboxOptionsAnswer, UUID> {
    List<QuestionCheckboxOptionsAnswer> findAllByCreatedBy(UUID sub);
}
