package com.learningpulse.classroom;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.List;

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

    /// Subject id of users
    private List<UUID> members;
    /// UUID of tests attached to this classroom
    private List<UUID> tests;

}