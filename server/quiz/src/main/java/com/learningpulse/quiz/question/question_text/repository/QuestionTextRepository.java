package com.learningpulse.quiz.question.question_text.repository;

import com.learningpulse.quiz.question.question_text.model.QuestionText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionTextRepository extends JpaRepository<QuestionText, UUID> {
    List<QuestionText> findAllByCreatedBy(UUID sub);
}
