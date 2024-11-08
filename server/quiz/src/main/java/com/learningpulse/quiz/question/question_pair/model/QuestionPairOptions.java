package com.learningpulse.quiz.question.question_pair.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionAnswer;
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

    // FIXME this is an incredibly hacky way to do this, should be done by using @JoinColumn
    @JsonBackReference("right-questionPairCollectionPair")
    @OneToOne
    private QuestionPairCollectionPair rightQuestionPairCollectionPair;

    @JsonBackReference("left-questionPairCollectionPair")
    @OneToOne
    private QuestionPairCollectionPair leftQuestionPairCollectionPair;

    private String contents;

    // FIXME same thing again
    @JsonBackReference("right-questionPairCollectionAnswer")
    @ManyToOne
    private QuestionPairCollectionAnswer rightQuestionPairCollectionAnswer;

    @JsonBackReference("left-questionPairCollectionAnswer")
    @ManyToOne
    private QuestionPairCollectionAnswer leftQuestionPairCollectionAnswer;
}
