package com.learningpulse.classroom;

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
@Table(schema = "learning_pulse"

// , uniqueConstraints = { @UniqueConstraint(columnNames = { "classroom_id",
// "user_id" })}
)

public class ClassroomMember implements Serializable {

    @Id
    // Id of the classroom
    @ManyToOne
    @JoinColumn(name = "classroom_id", nullable = false)
    private Classroom classroom_id;

    private UUID user_id;

}