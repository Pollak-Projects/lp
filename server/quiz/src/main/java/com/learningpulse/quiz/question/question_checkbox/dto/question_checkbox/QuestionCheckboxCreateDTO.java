package com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox;

import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options.QuestionCheckboxOptionsEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuestionCheckboxCreateDTO(
        UUID quizId,
        String title,
        List<QuestionCheckboxOptionsEmbedCreateDTO> options
) {
}
