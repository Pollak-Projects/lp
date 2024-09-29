package com.learningpulse.quiz.question.question_pair;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionPairCollectionPairRepository extends JpaRepository<QuestionPairCollectionPair, UUID> {
}
