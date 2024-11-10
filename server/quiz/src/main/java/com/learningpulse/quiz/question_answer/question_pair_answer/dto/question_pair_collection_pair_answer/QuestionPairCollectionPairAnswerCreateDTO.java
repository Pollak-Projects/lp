package com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_answer;

import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_options_answer.QuestionPairCollectionPairOptionsAnswerEmbedCreateDTO;

import java.util.UUID;

public record QuestionPairCollectionPairAnswerCreateDTO(
        UUID questionPairCollectionAnswerId,
        UUID questionPairCollectionPairId,
        QuestionPairCollectionPairOptionsAnswerEmbedCreateDTO left,
        QuestionPairCollectionPairOptionsAnswerEmbedCreateDTO right
) {
}
