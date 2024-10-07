package com.learningpulse.classroom.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

// This is why this is needed: https://stackoverflow.com/questions/25711858/spring-cant-determine-generic-types-when-lambda-expression-is-used-instead-of-a
interface AuthoritiesConverter extends Converter<Jwt, Collection<? extends GrantedAuthority>> {
}
