package com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_options_answer;

import java.util.UUID;

public record QuestionOrderOptionsAnswerUpdateDTO(
        UUID questionOrderOptionsAnswerId,
        UUID questionOrderAnswerId,
        UUID questionOptionsId,
        Integer order
) {
}
