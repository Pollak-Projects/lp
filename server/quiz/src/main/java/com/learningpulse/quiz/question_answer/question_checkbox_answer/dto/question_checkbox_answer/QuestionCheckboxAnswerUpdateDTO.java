package com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_answer;

import java.util.List;
import java.util.UUID;

public record QuestionCheckboxAnswerUpdateDTO(
        UUID questionCheckboxAnswerId,
        UUID quizAnswerId,
        UUID questionCheckboxId,
        List<UUID> options
) {
}
