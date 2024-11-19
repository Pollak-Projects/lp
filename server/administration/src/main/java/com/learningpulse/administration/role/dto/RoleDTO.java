package com.learningpulse.administration.role.dto;

public record RoleDTO(
        String id,
        String name,
        String description,
        Boolean composite
) {
}
