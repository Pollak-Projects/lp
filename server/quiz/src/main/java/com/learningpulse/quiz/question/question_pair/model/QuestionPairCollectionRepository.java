package com.learningpulse.quiz.question.question_pair.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionPairCollectionRepository extends JpaRepository<QuestionPairCollection, UUID> {
}
