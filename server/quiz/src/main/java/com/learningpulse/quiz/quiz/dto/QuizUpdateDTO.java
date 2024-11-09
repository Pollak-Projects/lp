package com.learningpulse.quiz.quiz.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record QuizUpdateDTO(
        UUID quizId,
        String name,
        String description,
        LocalDateTime deadline,
        Boolean viewAfterSubmission,
        List<UUID> questionCheckboxes,
        List<UUID> questionFiles,
        List<UUID> questionOrders,
        List<UUID> questionPairCollections,
        List<UUID> questionRadios,
        List<UUID> questionTexts
) {
}
