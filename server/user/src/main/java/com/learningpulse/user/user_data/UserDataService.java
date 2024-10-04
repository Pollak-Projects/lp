package com.learningpulse.user.user_data;

import com.learningpulse.user.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDataService {
    private final UserDataRepository userDataRepository;

    public UUID getProfileImageById(UUID id) {
        return userDataRepository.findById(id).orElse(UserData.builder().build()).getProfilePicture();
    }

    public UUID uploadProfileImage(UUID id, @NotNull UserDataRequest userDataRequest) {
        userDataRepository.save(UserData.builder()
                .id(id)
                .profilePicture(userDataRequest.Image())
                .build());
        return userDataRequest.Image();
    }

    public UUID updateProfileImage(UUID id, @NotNull UserDataRequest userDataRequest) {
        final var userData = userDataRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userData.setProfilePicture(userDataRequest.Image());

        userDataRepository.save(userData);

        return userData.getProfilePicture();
    }

}
