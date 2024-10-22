package com.learningpulse.classroom.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "learning_pulse", name = "classroom_members"

// , uniqueConstraints = { @UniqueConstraint(columnNames = { "classroom_id",
// "user_id" })}
)

public class Member implements Serializable {

    @Id
    // Id of the classroom
    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    public Classroom classroom_id;

    public UUID user_id;

}