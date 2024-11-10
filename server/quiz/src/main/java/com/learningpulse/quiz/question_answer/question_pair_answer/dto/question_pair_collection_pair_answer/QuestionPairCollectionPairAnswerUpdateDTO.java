package com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_answer;

import java.util.UUID;

public record QuestionPairCollectionPairAnswerUpdateDTO(
        UUID questionPairCollectionPairAnswerId,
        UUID questionPairCollectionPairId,
        UUID left,
        UUID right
) {
}
