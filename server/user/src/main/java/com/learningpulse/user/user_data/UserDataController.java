package com.learningpulse.user.user_data;

import com.learningpulse.user.config.KeycloakJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserDataController {
    private final UserDataService userDataService;

    @GetMapping("/webclient")
    public ResponseEntity<UserData> webclient() {
        return ResponseEntity.ok(UserData.builder()
                .id(UUID.randomUUID())
                .profilePicture(null)
                .build());
    }

    @GetMapping("/profileImage")
    public Mono<ResponseEntity<Mono<UUID>>> getProfileImageById(@AuthenticationPrincipal KeycloakJwt jwt) {
        return Mono.fromCallable(() -> ResponseEntity.ok(userDataService.getProfileImageById(jwt.getSub())));
    }

    @PostMapping("/profileImage")
    public Mono<ResponseEntity<Mono<UUID>>> uploadProfileImage(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody UserDataRequest userDataRequest) {
        return Mono.fromCallable(() -> ResponseEntity.ok(userDataService.uploadProfileImage(jwt.getSub(), userDataRequest)));
    }

    @PutMapping("/profileImage")
    public Mono<ResponseEntity<Mono<UUID>>> updateProfileImage(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody UserDataRequest userDataRequest) {
        return Mono.fromCallable(() -> ResponseEntity.ok(userDataService.updateProfileImage(jwt.getSub(), userDataRequest)));
    }

}
