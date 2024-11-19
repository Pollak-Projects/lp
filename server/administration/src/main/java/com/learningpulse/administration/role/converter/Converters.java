package com.learningpulse.administration.role.converter;

import com.learningpulse.administration.role.dto.RoleDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Converters {
    @Bean
    public RoleConverter roleConverter() {
        return roleRepresentation -> new RoleDTO(
                    roleRepresentation.getId(),
                    roleRepresentation.getName(),
                    roleRepresentation.getDescription(),
                    roleRepresentation.isComposite()
            );
    }
}
