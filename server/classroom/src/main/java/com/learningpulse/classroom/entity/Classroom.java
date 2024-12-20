package com.learningpulse.classroom.entity;

import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.annotation.CreatedBy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "classroom", schema = "learning_pulse")
public class Classroom implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private Timestamp createdAt;

    /// The code that users can join via
    /// 23^10 lots.....
    @Column(unique = true, nullable = false, length = 10)
    private String joinCode;

    @ElementCollection
    @CollectionTable(name = "classroom_members", schema = "learning_pulse", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "uuid")
    @Builder.Default
    private List<UUID> members = new ArrayList<UUID>();

    @CreatedBy
    private UUID createdBy;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public void addMember(UUID memberId) {
        this.members.add(memberId);
    }

}
