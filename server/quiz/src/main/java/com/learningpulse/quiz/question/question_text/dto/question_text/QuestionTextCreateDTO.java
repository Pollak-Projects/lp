package com.learningpulse.quiz.question.question_text.dto.question_text;

import java.util.UUID;

public record QuestionTextCreateDTO(
        UUID quizId,
        String title,
        String answer
) {
}

