package com.learningpulse.administration.user;

import com.learningpulse.administration.user.converter.UserRequestDTOConverter;
import com.learningpulse.administration.user.converter.UserResourceConverter;
import com.learningpulse.administration.user.converter.UserRepresentationConverter;
import com.learningpulse.administration.user.dto.UserDTO;
import com.learningpulse.administration.user.dto.UserRepresentationDTO;
import com.learningpulse.administration.user.dto.UserRequestDTO;
import com.learningpulse.administration.user.dto.UserResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final Keycloak keycloak;
    private final UserResourceConverter userResourceConverter;
    private final UserRepresentationConverter userRepresentationDTOConverter;
    private final UserRequestDTOConverter userRequestDTOConverter;

    @Value("${KC_REALM}")
    private String realm;

    public int countUsers() {
        return keycloak.realm(realm).users().count();
    }

    public List<UserRepresentationDTO> listUsers(String search, int page, int size) {
        return keycloak.realm(realm).users().search(search, page, size, false).stream()
                .map(userRepresentationDTOConverter::convert)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(String id) {
        return userResourceConverter.convert(keycloak.realm(realm).users().get(id));
    }

    public UserResponseDTO createUser(@NotNull UserRequestDTO user) {
        log.info("Creating user: username: {}, email: {}", user.username(), user.email());

        final var response = keycloak.realm(realm).users().create(userRequestDTOConverter.convert(user));

        log.info("Response status: {} {}", response.getStatus(), response.getStatusInfo());

        return new UserResponseDTO(
                response.getStatus(), response.readEntity(String.class)
        );
    }


    public void updateUser(@NotNull UserRequestDTO user) {
        final var userRepresentation = keycloak.realm(realm).users().get(user.id()).toRepresentation();
        userRepresentation.setUsername(user.username());
        userRepresentation.setEmail(user.email());
        userRepresentation.setFirstName(user.firstName());
        userRepresentation.setLastName(user.lastName());
        userRepresentation.setAttributes(user.attributes());

        keycloak.realm(realm).users().get(user.id()).update(userRepresentation);
    }

    public UserResponseDTO deleteUserById(@NotNull String id) {
        try (var response = keycloak.realm(realm).users().delete(id)) {
            log.info("Response status: {} {}", response.getStatus(), response.getStatusInfo());

            return new UserResponseDTO(
                    response.getStatus(), response.readEntity(String.class)
            );
        }
    }
}
