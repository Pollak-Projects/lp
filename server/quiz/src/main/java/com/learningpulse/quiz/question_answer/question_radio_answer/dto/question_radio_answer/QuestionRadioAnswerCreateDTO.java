package com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_answer;

import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_options_answer.QuestionRadioOptionsAnswerEmbedCreateDTO;

import java.util.List;
import java.util.UUID;

public record QuestionRadioAnswerCreateDTO(
        UUID quizAnswerId,
        UUID questionRadioId,
        List<QuestionRadioOptionsAnswerEmbedCreateDTO> options
) {
}
