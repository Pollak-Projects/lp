package com.learningpulse.quiz.question_answer.question_file_answer.dto.question_file_answer;

import java.util.UUID;

// TODO when file is implemented
public record QuestionFileAnswerUpdateDTO(
        UUID questionFileAnswerId,
        UUID quizAnswerId,
        UUID questionFileId,
        UUID fileId
) {
}
