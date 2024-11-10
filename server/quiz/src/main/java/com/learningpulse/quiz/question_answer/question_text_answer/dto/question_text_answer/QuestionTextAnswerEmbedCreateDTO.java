package com.learningpulse.quiz.question_answer.question_text_answer.dto.question_text_answer;

import java.util.UUID;

public record QuestionTextAnswerEmbedCreateDTO(
        UUID questionTextId,
        String answer
) {
}
