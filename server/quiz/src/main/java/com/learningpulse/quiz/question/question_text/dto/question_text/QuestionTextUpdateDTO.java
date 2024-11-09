package com.learningpulse.quiz.question.question_text.dto.question_text;

import java.util.UUID;

public record QuestionTextUpdateDTO(
        UUID questionTextId,
        String title,
        String answer,
        UUID quizId
) {
}
