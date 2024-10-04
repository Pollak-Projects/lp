package com.learningpulse.user.user_data;

import com.learningpulse.user.exception.HttpStatusCodeException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDataService {
    // FIXME this is currently broken WILL NOT COMPILE
    private final UserDataRepository userDataRepository;

    public Mono<UUID> getProfileImageById(UUID id) throws HttpStatusCodeException {
        return Mono.fromCallable(() -> userDataRepository
                .findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("User not found", HttpStatus.NOT_FOUND))
                .getProfilePicture());
    }

    public Mono<UUID> uploadProfileImage(UUID id, @NotNull UserDataRequest userDataRequest) {
        return Mono.fromCallable(() -> {
            userDataRepository.save(UserData.builder()
                    .id(id)
                    .profilePicture(userDataRequest.Image())
                    .build());
            return userDataRequest.Image();
        });
    }

    public Mono<UUID> updateProfileImage(UUID id, @NotNull UserDataRequest userDataRequest) {
        final var userData = userDataRepository
                .findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("User not found", HttpStatus.NOT_FOUND));

        userData.setProfilePicture(userDataRequest.Image());

        userDataRepository.save(userData);

        return userData.getProfilePicture();
    }

}
