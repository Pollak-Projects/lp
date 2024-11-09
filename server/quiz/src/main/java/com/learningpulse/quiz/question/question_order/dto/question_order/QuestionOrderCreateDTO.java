package com.learningpulse.quiz.question.question_order.dto.question_order;

import com.learningpulse.quiz.question.question_order.dto.question_order_options.QuestionOrderOptionsEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuestionOrderCreateDTO(
        UUID quizId,
        String title,
        List<QuestionOrderOptionsEmbedCreateDTO> options
) {
}
