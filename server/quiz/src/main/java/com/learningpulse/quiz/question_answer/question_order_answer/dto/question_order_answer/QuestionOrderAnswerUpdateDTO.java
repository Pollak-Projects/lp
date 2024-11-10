package com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_answer;

import java.util.List;
import java.util.UUID;

public record QuestionOrderAnswerUpdateDTO(
        UUID questionOrderAnswerId,
        UUID questionAnswerId,
        UUID questionOrderId,
        List<UUID> options
) {
}
