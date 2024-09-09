package com.learningpulse.forum;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "forum", schema = "forum")
/**
 * The forum database entry
 */
public class Forum implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @CreatedDate
    private Timestamp createdAt;

    /// The code that users can join via
    private String joinCode;

}