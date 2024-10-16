package com.learningpulse.classroom;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    // TODO make connection to other table somehow as this is a violation of 2nf
    @CreatedBy
    private UUID createdBy;

    @Builder.Default
    @OneToMany
    @JoinTable(name = "classroom_members", schema = "learning_pulse")
    private List<ClassroomMember> members = new ArrayList<ClassroomMember>();

    private UUID chat;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }
}