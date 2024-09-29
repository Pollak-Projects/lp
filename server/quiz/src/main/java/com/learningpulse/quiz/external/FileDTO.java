package com.learningpulse.quiz.external;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public record FileDTO(
        UUID id,
        String name,
        String mimeType,
        Timestamp createdAt,
        Timestamp updatedAt,
        // Todo ask @nezsha
//     ClassroomDTO classroom;
        UserDTO owner,
        Set<UserDTO> sharedWith,
        String key,
        boolean locked,
        boolean systemFile
) {
}
