package com.learningpulse.user.user_data;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    @Id
    private UUID id;

    // TODO add actual class
    private UUID profilePicture;
}
