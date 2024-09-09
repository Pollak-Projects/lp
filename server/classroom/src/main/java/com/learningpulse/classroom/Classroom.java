package com.learningpulse.classroom;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classroom", schema = "classroom")
/**
 * The forum database entry
 */
public class Classroom implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @CreatedDate
    private Timestamp createdAt;

    /// The code that users can join via
    private String joinCode;
    private UUID creatorId;

    private java.util.List<UUID> members;

}