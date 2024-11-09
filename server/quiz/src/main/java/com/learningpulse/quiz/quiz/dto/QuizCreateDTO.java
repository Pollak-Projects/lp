package com.learningpulse.quiz.quiz.dto;

import java.time.LocalDateTime;

public record QuizCreateDTO(
        String name,
        String description,
        LocalDateTime deadline,
        Boolean viewAfterSubmission
) {
}
