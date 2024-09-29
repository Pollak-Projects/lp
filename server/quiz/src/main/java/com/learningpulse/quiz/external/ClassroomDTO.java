package com.learningpulse.quiz.external;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public record ClassroomDTO(
        UUID id,
        String name,
        Timestamp createdAt,
        String joinCode,
        UUID creatorId,
        Set<UserDTO> members,
        UUID tests
) {
}
