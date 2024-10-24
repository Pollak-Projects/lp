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

    @CreatedBy
    private UUID createdBy;

    @Builder.Default
    @OneToMany(mappedBy = "classroom_id")
    private Set<Member> members = new HashSet<Member>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

}
