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
@Table(name = "USER", schema = "USER")
public class User {
    @Id
    private UUID id;

    // TODO add actual class
    @OneToOne
    private UUID profilePicture;
}
