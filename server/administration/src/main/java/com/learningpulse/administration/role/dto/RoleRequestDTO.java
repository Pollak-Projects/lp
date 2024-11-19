package com.learningpulse.administration.role.dto;

import java.util.List;

public record RoleRequestDTO(
        String id,
        List<String> roleNames
) {
}
