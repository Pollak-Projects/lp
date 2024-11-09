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
    // Its now defined with @JoinColumn, yet its still as hacky as before
    @JsonBackReference("left-questionPairCollectionPair")
    @OneToOne(mappedBy = "left", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private QuestionPairCollectionPair leftQuestionPairCollectionPair;

    @JsonBackReference("right-questionPairCollectionPair")
    @OneToOne(mappedBy = "right", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private QuestionPairCollectionPair rightQuestionPairCollectionPair;

    private String content;

    // FIXME same thing again, this may need to be moved to question_pair_answer
    @JsonBackReference("right-questionPairCollectionAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_pair_collection_answer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private QuestionPairCollectionAnswer rightQuestionPairCollectionAnswer;

    @JsonBackReference("left-questionPairCollectionAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_pair_collection_answer_id", referencedColumnName = "id", insertable = false, updatable = false)
    private QuestionPairCollectionAnswer leftQuestionPairCollectionAnswer;
}
