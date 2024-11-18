package com.learningpulse.quiz.quiz.dto;

import java.util.List;
import java.util.UUID;

public record QuizGetNamesByQuizAnswerIdsDTO(
        List<UUID> quizAnswerIds
) {
}
