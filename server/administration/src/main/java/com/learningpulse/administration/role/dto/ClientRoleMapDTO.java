package com.learningpulse.administration.role.dto;

import java.util.List;

public record ClientRoleMapDTO(
        String clientId,
        String clientName,
        List<RoleDTO> clientRoles
) {
}
