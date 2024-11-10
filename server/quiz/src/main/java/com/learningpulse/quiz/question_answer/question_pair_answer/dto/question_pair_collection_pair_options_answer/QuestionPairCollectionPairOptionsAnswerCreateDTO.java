package com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_options_answer;

import java.util.UUID;

public record QuestionPairCollectionPairOptionsAnswerCreateDTO(
        UUID questionPairCollectionPairAnswerId,
        String content
) {
}
