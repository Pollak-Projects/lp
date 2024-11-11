package com.learningpulse.quiz.question_answer.question_pair_answer.repository;

import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionPairOptionsAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionPairCollectionPairOptionsAnswerRepository extends JpaRepository<QuestionPairCollectionPairOptionsAnswer, UUID> {
}
