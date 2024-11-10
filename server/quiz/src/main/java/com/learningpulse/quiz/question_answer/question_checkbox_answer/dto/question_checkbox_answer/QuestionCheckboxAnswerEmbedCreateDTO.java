package com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_answer;

import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_options_answer.QuestionCheckboxOptionsAnswerEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuestionCheckboxAnswerEmbedCreateDTO(
        UUID questionCheckboxId,
        List<QuestionCheckboxOptionsAnswerEmbedCreateDTO> options
) {
}
