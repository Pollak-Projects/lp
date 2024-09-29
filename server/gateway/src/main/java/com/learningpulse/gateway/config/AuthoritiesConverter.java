package com.learningpulse.gateway.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

// This is why this is needed: https://stackoverflow.com/questions/25711858/spring-cant-determine-generic-types-when-lambda-expression-is-used-instead-of-a
interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {
}
