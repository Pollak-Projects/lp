# Login docs

> **Important** \
> This is just an idea pad on how to get our code to work, with keycloak.

### Keycloak with in the frontend

[Keycloak docs](https://www.keycloak.org/docs/latest/server_development/#_themes) this is how we can make a custom login page.

### Keycloak with in the backend

[Bealdung](https://www.baeldung.com/spring-boot-keycloak) this may be useful for the backend.

---

# Getting Started

---
From [lambda](https://github.com/Pollak-Projects/Lambda/blob/main/lambda-server/HELP.md)

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

```yml
# docker-compose for keycloak
services:
  keycloak:
    container_name: baeldung-keycloak.openid-provider
    image: quay.io/keycloak/keycloak:25.0.1
    command:
      - start-dev
      - --import-realm
    ports:
      - 8080:8080
    volumes:
      - ./keycloak/:/opt/keycloak/data/import/
    environment:
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: ${KEYCLOAK_ADMIN_PASSWORD}
      KC_HTTP_PORT: 8080
      KC_HOSTNAME_URL: http://localhost:8080
      KC_HOSTNAME_ADMIN_URL: http://localhost:8080
      KC_HOSTNAME_STRICT_BACKCHANNEL: true
      KC_HTTP_RELATIVE_PATH: /
      KC_HTTP_ENABLED: true
      KC_HEALTH_ENABLED: true
      KC_METRICS_ENABLED: true
    extra_hosts:
      - "host.docker.internal:host-gateway"
    healthcheck:
      test: [ 'CMD-SHELL', '[ -f /tmp/HealthCheck.java ] || echo "public class HealthCheck { public static void main(String[] args) throws java.lang.Throwable { System.exit(java.net.HttpURLConnection.HTTP_OK == ((java.net.HttpURLConnection)new java.net.URL(args[0]).openConnection()).getResponseCode() ? 0 : 1); } }" > /tmp/HealthCheck.java && java /tmp/HealthCheck.java http://localhost:8080/auth/health/live' ]
      interval: 5s
      timeout: 5s
      retries: 20
```