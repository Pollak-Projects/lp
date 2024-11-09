package com.learningpulse.quiz.question.question_pair.dto.question_pair_collection_pair;

import com.learningpulse.quiz.question.question_pair.dto.question_pair_options.QuestionPairOptionsEmbedCreateDTO;

import java.util.UUID;

public record QuestionPairCollectionPairCreateDTO(
        UUID belongsToQuestionPairCollectionId,
        QuestionPairOptionsEmbedCreateDTO left,
        QuestionPairOptionsEmbedCreateDTO right
) {
}
