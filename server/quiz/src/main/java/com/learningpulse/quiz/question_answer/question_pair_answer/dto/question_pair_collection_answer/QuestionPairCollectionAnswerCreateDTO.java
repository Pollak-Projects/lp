package com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_answer;

import com.learningpulse.quiz.question_answer.question_pair_answer.dto.question_pair_collection_pair_answer.QuestionPairCollectionPairAnswerEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuestionPairCollectionAnswerCreateDTO(
        UUID quizAnswerId,
        UUID questionPairCollectionId,
        List<QuestionPairCollectionPairAnswerEmbedCreateDTO> pairs
) {
}
