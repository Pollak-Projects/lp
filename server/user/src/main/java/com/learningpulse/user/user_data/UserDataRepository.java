package com.learningpulse.user.user_data;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UserDataRepository extends R2dbcRepository<UserData, UUID> {
    Mono<UserData> findByUserId(UUID userId);
}
