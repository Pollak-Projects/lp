package com.learningpulse.post;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post", schema = "forum")
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private UUID id; // id of the post itself

    private UUID groupID; // the group it's published to
    private String message; // contents of the message
    private UUID sentBy; // id of the user that published it
    private String title; // title
    
    @CreationTimestamp
    private Timestamp publishDate; // unix timestamp at time of creation
    
    private List<Attachasdment> attachments; // list of attachments, either link or file

    // TODO: add list of replies
    // @onetoomeany whatever
    // private List<Reply> replies;

}