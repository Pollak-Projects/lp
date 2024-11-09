package com.learningpulse.quiz.question.question_pair.dto.question_pair_collection_pair;

import com.learningpulse.quiz.question.question_pair.dto.question_pair_options.QuestionPairOptionsEmbedCreateDTO;

public record QuestionPairCollectionPairEmbedCreateDTO(
        QuestionPairOptionsEmbedCreateDTO left,
        QuestionPairOptionsEmbedCreateDTO right
) {
}
