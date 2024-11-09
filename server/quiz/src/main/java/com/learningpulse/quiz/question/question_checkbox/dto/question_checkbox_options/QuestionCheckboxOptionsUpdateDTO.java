package com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options;

import java.util.UUID;

public record QuestionCheckboxOptionsUpdateDTO(
        UUID questionCheckboxOptionId,
        UUID questionCheckboxId,
        String name,
        Boolean answer
) {
}
