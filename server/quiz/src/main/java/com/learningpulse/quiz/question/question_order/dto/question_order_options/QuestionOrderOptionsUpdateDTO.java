package com.learningpulse.quiz.question.question_order.dto.question_order_options;

import java.util.UUID;

public record QuestionOrderOptionsUpdateDTO(
        UUID questionOrderOptionsId,
        UUID questionOrderId,
        String title,
        Integer place
) {
}
