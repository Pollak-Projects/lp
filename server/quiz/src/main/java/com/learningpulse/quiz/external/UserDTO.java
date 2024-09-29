package com.learningpulse.quiz.external;

import java.util.UUID;


public record UserDTO(
        UUID id,
        FileDTO profilePicture
) {
}