package com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_options_answer;

import java.util.UUID;

public record QuestionRadioOptionsAnswerEmbedCreateDTO(
        UUID questionRadioOptionsId,
        String answer
) {
}
