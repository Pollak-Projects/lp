package com.learningpulse.classroom.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;

interface AuthoritiesConverter extends Converter<Jwt, Collection<? extends GrantedAuthority>> {
}
