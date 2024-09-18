package com.learningpulse.drive;

import com.learningpulse.drive.external.UserDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
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
public class File implements Serializable {
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
//    private ClassroomDTO classroom;

    @CreatedBy
    @ManyToOne
    private UserDTO owner;

    @ManyToMany
    private Set<UserDTO> sharedWith;

    private String key;

    private boolean locked;

    private boolean systemFile;
}
