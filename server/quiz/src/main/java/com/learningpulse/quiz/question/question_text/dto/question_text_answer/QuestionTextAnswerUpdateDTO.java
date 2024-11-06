package com.learningpulse.quiz.question.question_text.dto.question_text_answer;

import java.util.UUID;

public record QuestionTextAnswerUpdateDTO(
        UUID id,
        UUID belongsToId,
        String answer
) {
}
