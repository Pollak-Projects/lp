package com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_options_answer;

import java.util.UUID;

public record QuestionOrderOptionsAnswerEmbedCreateDTO(
        UUID questionOptionsId,
        Integer order
) {
}
