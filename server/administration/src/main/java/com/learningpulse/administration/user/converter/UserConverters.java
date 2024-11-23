package com.learningpulse.administration.user.converter;

import com.learningpulse.administration.role.converter.ClientRoleMapConverter;
import com.learningpulse.administration.role.converter.RoleConverter;
import com.learningpulse.administration.role.dto.ClientRoleMapDTO;
import com.learningpulse.administration.user.dto.UserDTO;
import com.learningpulse.administration.user.dto.UserRepresentationDTO;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserConverters {
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
    public UserResourceConverter userResourceConverter(RoleConverter roleConverter, ClientRoleMapConverter clientRoleMapConverter) {
        return userResource -> new UserDTO(
                    userResource.toRepresentation().getId(),
                    userResource.toRepresentation().getUsername(),
                    userResource.toRepresentation().isEnabled(),
                    userResource.toRepresentation().getFirstName(),
                    userResource.toRepresentation().getLastName(),
                    userResource.toRepresentation().getEmail(),
                    userResource.toRepresentation().getAttributes(),
                userResource.roles().realmLevel().listAll().stream().map(roleConverter::convert).toList(),
                userResource.roles().getAll().getClientMappings().values().stream().map(clientRoleMapConverter::convert).toList()
            );

    }

    @Bean
    public UserRequestDTOConverter userRequestDTOConverter() {
        return userRequestDTO -> {
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(userRequestDTO.password());
            credentialRepresentation.setTemporary(false);

            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setUsername(userRequestDTO.username());
            userRepresentation.setEnabled(true);
            userRepresentation.setFirstName(userRequestDTO.firstName());
            userRepresentation.setLastName(userRequestDTO.lastName());
            userRepresentation.setEmail(userRequestDTO.email());
            userRepresentation.setAttributes(userRequestDTO.attributes());
            userRepresentation.setCredentials(List.of(credentialRepresentation));

            return userRepresentation;
        };
    }

}
