package com.learningpulse.quiz.question.question_pair.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_PAIR_COLLECTION_ANSWER", schema = "quiz")
public class QuestionPairCollectionAnswer {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionPairCollectionAnswer")
    private Set<QuestionPairCollection> questionPairCollection;

    private UUID createdBy;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionPairCollectionAnswer")
    private Set<QuestionPairOptions> left;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionPairCollectionAnswer")
    private Set<QuestionPairOptions> right;
}
