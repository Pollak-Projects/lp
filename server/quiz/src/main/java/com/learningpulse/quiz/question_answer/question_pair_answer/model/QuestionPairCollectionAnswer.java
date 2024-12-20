package com.learningpulse.quiz.question_answer.question_pair_answer.model;

import com.fasterxml.jackson.annotation.*;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_PAIR_COLLECTION_ANSWER", schema = "quiz")
public class QuestionPairCollectionAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionPairCollectionAnswer-quizAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_answer_id", referencedColumnName = "id")
    private QuizAnswer belongsTo;

    private UUID createdBy;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_pair_collection_answer_to_id", referencedColumnName = "id")
    private QuestionPairCollection questionPairCollection;

    @JsonManagedReference("questionPairCollectionPairAnswer-questionPairCollectionAnswer")
    @OneToMany(mappedBy = "questionPairCollectionAnswer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QuestionPairCollectionPairAnswer> answerPairs;
}
