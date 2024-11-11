package com.learningpulse.quiz.question.question_order.dto.question_order;

import java.util.List;
import java.util.UUID;

public record QuestionOrderUpdateDTO(
        UUID questionOrderId,
        UUID quizId,
        String title,
        List<UUID> options
) {
}
