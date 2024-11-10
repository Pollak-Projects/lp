package com.learningpulse.quiz.question_answer.question_pair_answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.UUID;

// This class could be removed, as it may be unnecessary, not sure about that yet
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_PAIR_COLLECTION_PAIR_options_ANSWER", schema = "quiz")
public class QuestionPairCollectionPairOptionsAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionPairCollectionPairOptionsAnswer-questionPairCollectionPairAnswer")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "question_pair_collection_pair_answer_id", referencedColumnName = "id")
    private QuestionPairCollectionAnswer belongsTo;

    private String content;
}
