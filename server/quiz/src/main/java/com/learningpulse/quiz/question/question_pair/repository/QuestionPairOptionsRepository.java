package com.learningpulse.quiz.question.question_pair.repository;

import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPairOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionPairOptionsRepository extends JpaRepository<QuestionPairCollectionPairOptions, UUID> {
}
