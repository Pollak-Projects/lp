package com.learningpulse.classroom.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.oauth2.jwt.Jwt;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class KeycloakJwt implements Serializable {
    private Instant exp;
    private Instant iat;
    private UUID jti;
    private String iss;
    private ArrayList<String> aud;
    private UUID sub;
    private String typ;
    private String azp;
    private UUID sid;
    private String acr;
    private ArrayList<String> allowedOrigins;

    // These fields may contain different types of objects and lists
    private Object realmAccess;
    private Object resourceAccess;
    private Object account;

    private String scope;
    private boolean emailVerified;
    private String name;
    private String preferredUsername;
    private String givenName;
    private String familyName;
    private String email;

    public KeycloakJwt(@NotNull Jwt jwt) {
        this.exp = jwt.getClaim("exp");
        this.iat = jwt.getClaim("iat");
        this.jti = UUID.fromString(jwt.getClaim("jti"));
        this.iss = jwt.getClaim("iss");
        this.aud = jwt.getClaim("aud");
        this.sub = UUID.fromString(jwt.getClaim("sub"));
        this.typ = jwt.getClaim("typ");
        this.azp = jwt.getClaim("azp");
        this.sid = UUID.fromString(jwt.getClaim("sid"));
        this.acr = jwt.getClaim("acr");
        this.allowedOrigins = jwt.getClaim("allowed-origins");
        this.realmAccess = jwt.getClaim("realm_access");
        this.resourceAccess = jwt.getClaim("resource_access");
        this.account = jwt.getClaim("account");
        this.scope = jwt.getClaim("scope");
        this.emailVerified = jwt.getClaim("email_verified");
        this.name = jwt.getClaim("name");
        this.preferredUsername = jwt.getClaim("preferred_username");
        this.givenName = jwt.getClaim("given_name");
        this.familyName = jwt.getClaim("family_name");
        this.email = jwt.getClaim("email");
    }
}
