package com.learningpulse.quiz.question.question_radio.dto.question_radio_options;

import java.util.UUID;

public record QuestionRadioOptionsCreateDTO(
        UUID questionRadioId,
        String title,
        boolean answer
) {
}
