package com.learningpulse.user.external;

import java.util.UUID;


public record UserDTO(
        UUID id,
        FileDTO profilePicture
) {
}