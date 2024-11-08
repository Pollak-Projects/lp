package com.learningpulse.quiz.question.question_pair.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference("questionPairCollectionPair-questionPairCollection")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_pair_collection_id", referencedColumnName = "id")
    private QuestionPairCollection belongsTo;

    private UUID createdBy;

    // FIXME this might be wrong, because the same column is used for both left and right,
    //  but that's how it is in the database diagram
    @JsonManagedReference("left-questionPairCollectionPair")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_options_id", referencedColumnName = "id", insertable = false, updatable = false)
    private QuestionPairOptions left;

    @JsonManagedReference("right-questionPairCollectionPair")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_options_id", referencedColumnName = "id", insertable = false, updatable = false)
    private QuestionPairOptions right;
}
