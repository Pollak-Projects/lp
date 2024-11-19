package com.learningpulse.administration.user.converter;

import com.learningpulse.administration.user.dto.UserRepresentationDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.convert.converter.Converter;

public interface UserRepresentationConverter extends Converter<UserRepresentation, UserRepresentationDTO> {
}
