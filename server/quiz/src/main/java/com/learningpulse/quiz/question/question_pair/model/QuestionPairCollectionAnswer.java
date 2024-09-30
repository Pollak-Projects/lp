package com.learningpulse.quiz.question.question_pair.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_PAIR_COLLECTION_ANSWER", schema = "quiz")
public class QuestionPairCollectionAnswer {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @OneToMany(mappedBy = "questionPairCollectionAnswer")
    private Set<QuestionPairCollection> questionPairCollection;

    // TODO replace this with the actual user type
    // FIXME CreatedBy will not work right now because the Auditor haven't yet been tested
    @CreatedBy
    private UUID createdBy;

    @OneToMany(mappedBy = "questionPairCollectionAnswer")
    private Set<QuestionPairOptions> left;

    @OneToMany(mappedBy = "questionPairCollectionAnswer")
    private Set<QuestionPairOptions> right;
}
