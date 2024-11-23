package com.learningpulse.administration.user.converter;

import com.learningpulse.administration.user.dto.UserDTO;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.core.convert.converter.Converter;

public interface UserResourceConverter extends Converter<UserResource, UserDTO> {
}
