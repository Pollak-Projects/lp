package com.learningpulse.quiz.question.question_checkbox.repository;

import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionCheckboxOptionsRepository extends JpaRepository<QuestionCheckboxOptions, UUID> {
    List<QuestionCheckboxOptions> findAllByCreatedBy(UUID sub);

}
