package com.learningpulse.administration.user.converter;

import com.learningpulse.administration.user.dto.UserRequestDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.convert.converter.Converter;

public interface UserRequestDTOConverter extends Converter<UserRequestDTO, UserRepresentation> {
}
