package com.learningpulse.quiz.quiz_answer.dto;

import com.learningpulse.quiz.question_answer.question_text_answer.dto.question_text_answer.QuestionTextAnswerCreateDTO;

import java.util.List;
import java.util.UUID;

// TODO add fields
public record QuizAnswerCreateDTO(
        UUID quizId,
        UUID createdBy,
        List<QuestionTextAnswerCreateDTO> questionTextAnswers
) {
}
