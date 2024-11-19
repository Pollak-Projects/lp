package com.learningpulse.administration.role.converter;

import com.learningpulse.administration.role.dto.RoleDTO;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.core.convert.converter.Converter;

public interface RoleConverter extends Converter<RoleRepresentation, RoleDTO> {
}
