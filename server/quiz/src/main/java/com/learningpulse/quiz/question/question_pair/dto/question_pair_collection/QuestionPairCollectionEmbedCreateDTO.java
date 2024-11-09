package com.learningpulse.quiz.question.question_pair.dto.question_pair_collection;

import com.learningpulse.quiz.question.question_pair.dto.question_pair_collection_pair.QuestionPairCollectionPairEmbedCreateDTO;

import java.util.List;

public record QuestionPairCollectionEmbedCreateDTO(
        String title,
        List<QuestionPairCollectionPairEmbedCreateDTO> questionPairCollectionPairs
) {
}
