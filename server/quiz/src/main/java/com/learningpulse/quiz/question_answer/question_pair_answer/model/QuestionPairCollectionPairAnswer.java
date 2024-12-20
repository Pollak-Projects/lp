package com.learningpulse.quiz.question_answer.question_pair_answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollectionPair;
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
@Table(name = "QUESTION_PAIR_COLLECTION_PAIR_ANSWER", schema = "quiz")
public class QuestionPairCollectionPairAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    private UUID createdBy;

    @JsonBackReference("questionPairCollectionPairAnswer-questionPairCollectionAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_pair_collection_answer_id", referencedColumnName = "id")
    private QuestionPairCollectionAnswer questionPairCollectionAnswer;

    // Gonna leave this here, if someone needs it
    // @JsonIncludeProperties({"id"})
    // @JsonProperty("questionPairCollectionPairId")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_pair_collection_pair_answer_to_id", referencedColumnName = "id")
    private QuestionPairCollectionPair questionPairCollectionPair;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_PAIR_COLLECTION_PAIR_options_left_ANSWER_id", referencedColumnName = "id")
    private QuestionPairCollectionPairOptionsAnswer left;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_PAIR_COLLECTION_PAIR_options_right_ANSWER_id", referencedColumnName = "id")
    private QuestionPairCollectionPairOptionsAnswer right;
}
