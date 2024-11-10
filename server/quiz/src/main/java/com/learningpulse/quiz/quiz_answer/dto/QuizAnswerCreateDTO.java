package com.learningpulse.quiz.quiz_answer.dto;

import java.util.UUID;

public record QuizAnswerCreateDTO(
        UUID quizId
) {
}
