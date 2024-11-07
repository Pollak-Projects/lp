package com.learningpulse.quiz.question.question_text.dto.question_text;

import java.util.List;
import java.util.UUID;

public record QuestionTextUpdateDTO(
        UUID id,
        String title,
        String answer,
        UUID quizId,
        List<UUID> answerIds
) {
}
