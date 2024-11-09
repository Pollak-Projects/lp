package com.learningpulse.quiz.question.question_order.dto.question_order_options;

import java.util.UUID;

public record QuestionOrderOptionsCreateDTO(
        UUID questionOrderId,
        String title,
        Integer place
) {
}
