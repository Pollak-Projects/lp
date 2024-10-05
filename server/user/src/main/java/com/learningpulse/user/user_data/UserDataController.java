package com.learningpulse.user.user_data;

import com.learningpulse.user.config.KeycloakJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
                .userId(UUID.randomUUID())
                .profilePicture(null)
                .build());
    }

    @GetMapping("/profileImage")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UUID> getProfileImageById(@AuthenticationPrincipal KeycloakJwt jwt) {
        return userDataService.getProfileImageById(jwt.getSub());
    }

    @PostMapping("/addProfileImage")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserData> addProfileImage(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody UserDataRequest userDataRequest) {
        return userDataService.addProfileImage(jwt.getSub(), userDataRequest);
    }

    @PostMapping("/profileImage")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UUID> uploadProfileImage(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody UserDataRequest userDataRequest) {
        return userDataService.uploadProfileImage(jwt.getSub(), userDataRequest);
    }

    @PutMapping("/profileImage")
    @ResponseStatus(HttpStatus.OK)
    public Mono<UUID> updateProfileImage(@AuthenticationPrincipal KeycloakJwt jwt, @RequestBody UserDataRequest userDataRequest) {
        return userDataService.updateProfileImage(jwt.getSub(), userDataRequest);
    }

}
