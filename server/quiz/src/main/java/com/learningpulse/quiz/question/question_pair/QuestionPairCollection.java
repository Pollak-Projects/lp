package com.learningpulse.quiz.question.question_pair;

import com.learningpulse.quiz.Quiz;
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

    @ManyToOne
    private Quiz quiz;

    private String title;

    @OneToMany(mappedBy = "questionPairCollection")
    private Set<QuestionPairCollectionPair> options;

    @ManyToOne
    private QuestionPairCollectionAnswer questionPairCollectionAnswer;
}
