package com.learningpulse.administration.user.converter;

import com.learningpulse.administration.role.converter.RoleConverter;
import com.learningpulse.administration.user.dto.UserDTO;
import com.learningpulse.administration.user.dto.UserRepresentationDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class Converters {
    @Bean
    public UserRepresentationConverter userRepresentationDTOConverter() {
        return userResource -> new UserRepresentationDTO(
                    userResource.getId(),
                    userResource.getUsername(),
                    userResource.isEnabled(),
                    userResource.getFirstName(),
                    userResource.getLastName(),
                    userResource.getEmail(),
                    userResource.getAttributes(),
                    userResource.getRealmRoles(),
                    userResource.getGroups()
            );
    }

    @Bean
    public UserConverter userConverter(RoleConverter roleConverter) {
        return userResource -> new UserDTO(
                    userResource.toRepresentation().getId(),
                    userResource.toRepresentation().getUsername(),
                    userResource.toRepresentation().isEnabled(),
                    userResource.toRepresentation().getFirstName(),
                    userResource.toRepresentation().getLastName(),
                    userResource.toRepresentation().getEmail(),
                    userResource.toRepresentation().getAttributes(),
                    userResource.roles().realmLevel().listAll().stream().map(roleConverter::convert).toList()
            );

    }
}
