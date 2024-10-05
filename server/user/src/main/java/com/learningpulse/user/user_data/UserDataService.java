package com.learningpulse.user.user_data;

import com.learningpulse.user.exception.HttpStatusCodeException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDataService {
    // FIXME this is currently broken WILL NOT COMPILE
    private final UserDataRepository userDataRepository;

    public Mono<UUID> getProfileImageById(UUID id) throws HttpStatusCodeException {
        return userDataRepository
                .findById(id)
                .map(UserData::getProfilePicture)
                .switchIfEmpty(Mono.error(new HttpStatusCodeException("User not found", HttpStatus.NOT_FOUND)));
    }

    public Mono<UUID> uploadProfileImage(UUID id, @NotNull UserDataRequest userDataRequest) {
        return userDataRepository.save(UserData.builder()
                    .userId(id)
                    .profilePicture(userDataRequest.Image())
                    .build())
                .map(UserData::getProfilePicture)
                .switchIfEmpty(Mono.error(new HttpStatusCodeException("User not found", HttpStatus.NOT_FOUND)));
    }

    public Mono<UserData> addProfileImage(UUID userId, @NotNull UserDataRequest userDataRequest) {
        UserData newUser = new UserData(null, userId, userDataRequest.Image(), LocalDateTime.now());
        return userDataRepository.save(newUser)
                .switchIfEmpty(Mono.error(new HttpStatusCodeException("Failed to add profile image", HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    public Mono<UUID> updateProfileImage(UUID id, @NotNull UserDataRequest userDataRequest) {
        return userDataRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new HttpStatusCodeException("User not found", HttpStatus.NOT_FOUND)))
                .flatMap(user -> {
                    user.setProfilePicture(userDataRequest.Image());
                    return userDataRepository.save(user);
                })
                .map(UserData::getProfilePicture);
    }

}
