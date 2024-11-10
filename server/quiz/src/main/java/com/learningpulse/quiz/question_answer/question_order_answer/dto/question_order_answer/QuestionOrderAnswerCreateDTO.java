package com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_answer;

import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_options_answer.QuestionOrderOptionsAnswerEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuestionOrderAnswerCreateDTO(
        UUID questionAnswerId,
        UUID questionOrderId,
        List<QuestionOrderOptionsAnswerEmbedCreateDTO> options
) {
}
