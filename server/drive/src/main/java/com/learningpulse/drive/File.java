package com.learningpulse.drive;

import com.learningpulse.classroom.Classroom;
import com.learningpulse.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "FILE", schema = "FILE")
public class File {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String mimeType;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    // Todo ask @nezsha
    private Classroom classroom;

    @CreatedBy
    @ManyToOne
    private User owner;

    @ManyToMany
    private Set<User> sharedWith;

    private String key;

    private boolean locked;

    private boolean systemFile;
}
