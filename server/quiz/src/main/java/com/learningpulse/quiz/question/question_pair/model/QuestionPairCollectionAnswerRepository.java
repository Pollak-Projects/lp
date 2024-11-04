package com.learningpulse.quiz.question.question_pair.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionPairCollectionAnswerRepository extends JpaRepository<QuestionPairCollectionAnswer, UUID> {
    List<QuestionPairCollectionAnswer> findAllByCreatedBy(UUID sub);
    List<QuestionPairCollectionAnswer> findAllByQuestionPairCollectionId(UUID questionPairCollectionId);
}
