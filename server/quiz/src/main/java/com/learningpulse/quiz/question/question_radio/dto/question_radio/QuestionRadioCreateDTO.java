package com.learningpulse.quiz.question.question_radio.dto.question_radio;

import com.learningpulse.quiz.question.question_radio.dto.question_radio_options.QuestionRadioOptionsEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuestionRadioCreateDTO(
        UUID quizId,
        String title,
        List<QuestionRadioOptionsEmbedCreateDTO> options
) {
}
