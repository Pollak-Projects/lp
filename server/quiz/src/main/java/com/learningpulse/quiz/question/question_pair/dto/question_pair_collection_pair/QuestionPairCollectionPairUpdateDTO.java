package com.learningpulse.quiz.question.question_pair.dto.question_pair_collection_pair;

import java.util.UUID;

public record QuestionPairCollectionPairUpdateDTO(
        UUID questionPairCollectionPairId,
        UUID belongsToQuestionPairCollectionId,
        UUID leftQuestionPairOptionsId,
        UUID rightQuestionPairOptionsId
) {
}
