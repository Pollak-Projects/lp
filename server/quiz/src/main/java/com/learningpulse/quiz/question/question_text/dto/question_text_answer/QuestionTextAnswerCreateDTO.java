package com.learningpulse.quiz.question.question_text.dto.question_text_answer;

import java.util.UUID;

public record QuestionTextAnswerCreateDTO(
        UUID belongsToId,
        String answer
) {
}
