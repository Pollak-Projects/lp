package com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_options_answer;

import java.util.UUID;

public record QuestionCheckboxOptionsAnswerEmbedCreateDTO(
        UUID questionCheckboxOptionsId,
        Boolean answer
) {
}
