package com.learningpulse.quiz.question.question_pair.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_PAIR_COLLECTION_PAIR", schema = "quiz")
public class QuestionPairCollectionPair implements Serializable {
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
