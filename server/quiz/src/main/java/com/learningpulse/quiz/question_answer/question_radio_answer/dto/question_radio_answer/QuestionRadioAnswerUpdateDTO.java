package com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_answer;

import java.util.List;
import java.util.UUID;

public record QuestionRadioAnswerUpdateDTO(
        UUID questionRadioAnswerId,
        UUID quizAnswerId,
        UUID questionRadioId,
        List<UUID> options
) {
}
