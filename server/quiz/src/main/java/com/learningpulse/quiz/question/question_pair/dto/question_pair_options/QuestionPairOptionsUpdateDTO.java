package com.learningpulse.quiz.question.question_pair.dto.question_pair_options;

import java.util.UUID;

public record QuestionPairOptionsUpdateDTO(
        UUID questionPairOptionsId,
        UUID leftQuestionPairCollectionPairId,
        UUID rightQuestionPairCollectionPairId,
        String content
) {
}
