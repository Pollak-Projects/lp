package com.learningpulse.administration.role.converter;

import com.learningpulse.administration.role.dto.ClientRoleMapDTO;
import com.learningpulse.administration.role.dto.RoleDTO;
import org.apache.commons.lang3.tuple.Pair;
import org.keycloak.representations.idm.ClientMappingsRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Map;

public interface ClientRoleMapConverter extends Converter<ClientMappingsRepresentation, ClientRoleMapDTO> {
}
