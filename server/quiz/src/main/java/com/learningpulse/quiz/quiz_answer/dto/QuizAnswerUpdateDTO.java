package com.learningpulse.quiz.quiz_answer.dto;

import java.util.List;
import java.util.UUID;

public record QuizAnswerUpdateDTO(
        UUID quizAnswerId,
        UUID quizId,
        List<UUID> questionCheckboxAnswers,
        List<UUID> questionFileAnswers,
        List<UUID> questionOrderAnswers,
        List<UUID> questionPairCollectionAnswers,
        List<UUID> questionRadioAnswers,
        List<UUID> questionTextAnswers
) {
}
