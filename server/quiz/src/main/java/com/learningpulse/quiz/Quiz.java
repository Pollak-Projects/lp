package com.learningpulse.quiz;

// Useful link to understand the difference between @OntToOne and @ManyToOne: https://stackoverflow.com/questions/16119531/hibernate-jpa-manytoone-vs-onetomany
// https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html#entity-mapping-association

// TODO add (cascade = CascadeType.ALL, fetch = FetchType.EAGER) to all OneToMany and ManyToOne relationships


import com.learningpulse.quiz.question.type.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUIZ", schema = "QUIZ")
public class Quiz {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @ManyToOne
    @CreatedBy
    // TODO change this to User
    private UUID createdBy;

    @CreationTimestamp
    private Timestamp createdAt;

    private LocalDateTime deadline;

    private boolean viewAfterSubmission;

    // This seems really inefficient @nezsha please advise 2024-09-17

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionText> questionTexts;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionRadio> questionRadios;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionCheckbox> questionCheckboxes;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionFile> questionFiles;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionOrder> questionOrders;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionPairCollection> questionPairCollections;
}
