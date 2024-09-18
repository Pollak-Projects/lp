package com.learningpulse.drive.external;

import java.util.UUID;


public record UserDTO(
    UUID id,
    FileDTO profilePicture
) {}