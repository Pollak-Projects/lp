package com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox;

import java.util.List;
import java.util.UUID;

public record QuestionCheckboxUpdateDTO(
        UUID questionCheckboxId,
        UUID quizId,
        String title,
        List<UUID> optionIds
) {
}
