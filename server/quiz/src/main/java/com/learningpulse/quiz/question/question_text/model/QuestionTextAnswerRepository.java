package com.learningpulse.quiz.question.question_text.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionTextAnswerRepository extends JpaRepository<QuestionTextAnswer, UUID> {
    List<QuestionTextAnswer> findAllByCreatedBy(UUID sub);
    List<QuestionTextAnswer> findAllByBelongsTo(QuestionText belongsTo);

    void deleteAllByBelongsToId(UUID id);
}
