package com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options;

import java.util.UUID;

public record QuestionCheckboxOptionsCreateDTO(
        UUID questionCheckboxId,
        String name,
        Boolean answer
) {
}
