package com.learningpulse.quiz.question.question_checkbox.repository;

import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionCheckboxRepository extends JpaRepository<QuestionCheckbox, UUID> {
    List<QuestionCheckbox> findAllByCreatedBy(UUID sub);

}
