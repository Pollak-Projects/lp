package com.learningpulse.quiz.question.question_radio.repository;

import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRadioOptionsRepository extends JpaRepository<QuestionRadioOptions, UUID> {
    List<QuestionRadioOptions> findAllByCreatedBy(UUID sub);
}
