package com.learningpulse.quiz.question.question_file.dto.question_file;

import java.util.UUID;

// Todo add this when the service is implemented
public record QuestionFileEmbedCreateDTO(
        UUID fileId,
        String title
) {
}
