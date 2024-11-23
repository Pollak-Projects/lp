package com.learningpulse.administration.role.converter;

import com.learningpulse.administration.role.dto.ClientRoleMapDTO;
import com.learningpulse.administration.role.dto.RoleDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class RoleConverters {
    @Bean
    public RoleConverter roleConverter() {
        return roleRepresentation -> new RoleDTO(
                    roleRepresentation.getId(),
                    roleRepresentation.getName(),
                    roleRepresentation.getDescription(),
                    roleRepresentation.isComposite()
            );
    }

    @Bean
    public ClientRoleMapConverter clientRoleMapConverter(RoleConverter roleConverter) {
        return clientRoleMapRepresentation -> new ClientRoleMapDTO(
                clientRoleMapRepresentation.getId(),
                clientRoleMapRepresentation.getClient(),
                clientRoleMapRepresentation.getMappings().stream().map(roleConverter::convert).toList()
        );
    }
}
