package com.learningpulse.classroom;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classroom", schema = "learning_pulse")
// FIXME https://en.wikibooks.org/wiki/Java_Persistence/ElementCollection

public class Classroom implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Timestamp createdAt;

    /// The code that users can join via
    /// 10^10 = 10 000 000 000 possible classrooms
    @Column(unique = true, nullable = false, length = 10)
    private String joinCode;

    // TODO make connection to other table somehow as this is a violation of 2nf
    @CreatedBy
    private UUID createdBy;

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "classroom_members", schema = "learning_pulse", joinColumns = @JoinColumn(name = "userid"))
    private List<UUID> members = new ArrayList<UUID>();

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "post", schema = "learning_pulse", joinColumns = @JoinColumn(name = "id"))
    private List<UUID> posts = new ArrayList<UUID>();

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "classroom_attachments", schema = "learning_pulse", joinColumns = @JoinColumn(name = "id"))
    private List<UUID> file = new ArrayList<UUID>();

    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "classroom_assignments", schema = "learning_pulse", joinColumns = @JoinColumn(name = "id"))
    private List<UUID> assignments = new ArrayList<UUID>();

    private UUID chat;

    public void add_member(UUID user) {
        this.members.add(user);
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}