package com.learningpulse.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user", schema = "user_schema")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    // TODO add actual class
    private UUID profilePicture;
}
