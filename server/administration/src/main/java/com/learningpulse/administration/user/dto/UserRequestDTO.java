package com.learningpulse.administration.user.dto;

import java.util.List;
import java.util.Map;

public record UserRequestDTO(
        String id,
        String username,
        String email,
        String firstName,
        String lastName,
        String password,
        Map<String, List<String>> attributes
) {
}
