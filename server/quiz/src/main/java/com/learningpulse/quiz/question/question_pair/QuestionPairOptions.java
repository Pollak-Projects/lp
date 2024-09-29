package com.learningpulse.quiz.question.question_pair;

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
@Table(name = "QUESTION_PAIR_OPTIONS", schema = "quiz")
public class QuestionPairOptions implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private QuestionPairCollectionPair questionPairCollectionPair;

    private String contents;

    @ManyToOne
    private QuestionPairCollectionAnswer questionPairCollectionAnswer;
}
