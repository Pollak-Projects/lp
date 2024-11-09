package com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox;

import com.learningpulse.quiz.question.question_checkbox.dto.question_checkbox_options.QuestionCheckboxOptionsEmbedCreateDTO;

import java.util.List;

public record QuestionCheckboxEmbedCreateDTO(
        String title,
        List<QuestionCheckboxOptionsEmbedCreateDTO> options
) {
}
