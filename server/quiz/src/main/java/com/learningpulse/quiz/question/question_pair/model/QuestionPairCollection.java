package com.learningpulse.quiz.question.question_pair.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.quiz.Quiz;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_PAIR_COLLECTION", schema = "quiz")
public class QuestionPairCollection implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionPairCollection-quiz")
    @ManyToOne
    private Quiz quiz;

    private UUID createdBy;

    private String title;

    @JsonManagedReference("questionPairCollectionPair-questionPairCollection")
    @OneToMany(mappedBy = "belongsTo")
    private Set<QuestionPairCollectionPair> questionPairCollectionPairs;

    @JsonBackReference("questionPairCollectionAnswer-questionPairCollection")
    @ManyToOne
    private QuestionPairCollectionAnswer questionPairCollectionAnswer;
}
