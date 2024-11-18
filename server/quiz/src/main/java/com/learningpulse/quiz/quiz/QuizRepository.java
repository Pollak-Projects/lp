package com.learningpulse.quiz.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    Optional<Quiz> findByQuizAnswersId(UUID quizAnswerId);

    List<Quiz> findByCreatedBy(UUID createdBy);
}

