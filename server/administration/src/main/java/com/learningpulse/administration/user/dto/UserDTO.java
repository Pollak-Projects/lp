package com.learningpulse.administration.user.dto;

import com.learningpulse.administration.role.dto.ClientRoleMapDTO;
import com.learningpulse.administration.role.dto.RoleDTO;

import java.util.List;
import java.util.Map;

public record UserDTO(
        String id,
        String username,
        Boolean enabled,
        String firstName,
        String lastName,
        String email,
        Map<String, List<String>> attributes,
        List<RoleDTO> realmRoles,
        List<ClientRoleMapDTO> clientRoles
) {
}
