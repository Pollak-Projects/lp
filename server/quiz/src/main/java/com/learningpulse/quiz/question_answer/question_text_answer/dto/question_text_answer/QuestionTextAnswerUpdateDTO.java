package com.learningpulse.quiz.question_answer.question_text_answer.dto.question_text_answer;

import java.util.UUID;

public record QuestionTextAnswerUpdateDTO(
        UUID questionTextId,
        UUID belongsToId,
        String answer
) {
}
