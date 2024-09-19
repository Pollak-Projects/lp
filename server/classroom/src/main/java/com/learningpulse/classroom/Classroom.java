package com.learningpulse.classroom;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import java.util.Collections;
import java.util.List;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classroom", schema = "classroom")

public class Classroom implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @CreatedDate
    private Timestamp createdAt;

    /// The code that users can join via
    /// This should be a unique code ranging from 4-12 in length
    private String joinCode;

    @CreatedBy
    private UUID createdBy;

    // TODO replace with classes accordingly
    // TODO connect members table https:// www.baeldung.com/hibernate-many-to-many
    @Builder.Default
    private List<UUID> members = Collections.emptyList();

    @Builder.Default
    // TODO connect posts table https:// www.baeldung.com/hibernate-many-to-many
    private List<UUID> posts = Collections.emptyList();

    @Builder.Default
    // TODO connect files table https:// www.baeldung.com/hibernate-many-to-many
    private List<UUID> files = Collections.emptyList();

}