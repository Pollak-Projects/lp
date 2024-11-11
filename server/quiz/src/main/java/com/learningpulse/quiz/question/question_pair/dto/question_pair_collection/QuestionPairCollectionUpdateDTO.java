package com.learningpulse.quiz.question.question_pair.dto.question_pair_collection;

import java.util.List;
import java.util.UUID;

public record QuestionPairCollectionUpdateDTO(
        UUID questionPairCollectionId,
        UUID quizId,
        String title,
        List<UUID> pairs
) {
}
