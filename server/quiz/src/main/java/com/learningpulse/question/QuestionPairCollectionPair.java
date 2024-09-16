package com.learningpulse.question;

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
@Table(name = "QUESTION_PAIR_COLLECTION_PAIR", schema = "QUESTION_PAIR_COLLECTION_PAIR")
public class QuestionPairCollectionPair {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private QuestionPairCollection questionPairCollection;

    @OneToOne
    private QuestionPairOptions left;

    @OneToOne
    private QuestionPairOptions right;
}
