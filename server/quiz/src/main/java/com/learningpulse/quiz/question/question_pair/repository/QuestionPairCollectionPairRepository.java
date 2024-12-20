package com.learningpulse.quiz.question.question_pair.repository;

import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionPairCollectionPairRepository extends JpaRepository<QuestionPairCollectionPair, UUID> {
    List<QuestionPairCollectionPair> findAllByCreatedBy(UUID sub);

    List<QuestionPairCollectionPair> findAllByBelongsToId(UUID questionPairCollectionId);
}
