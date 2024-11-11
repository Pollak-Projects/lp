package com.learningpulse.quiz.question.question_radio.dto.question_radio;

import java.util.List;
import java.util.UUID;

public record QuestionRadioUpdateDTO(
        UUID questionRadioId,
        UUID quizId,
        String title,
        List<UUID> options
) {
}
