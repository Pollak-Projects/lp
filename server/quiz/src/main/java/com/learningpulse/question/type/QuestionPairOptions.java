package com.learningpulse.question.type;

import com.learningpulse.question.answer.QuestionPairCollectionAnswer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_PAIR_OPTIONS", schema = "QUESTION_PAIR_OPTIONS")
public class QuestionPairOptions {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    private QuestionPairCollectionPair questionPairCollectionPair;

    private String contents;

    @ManyToOne
    private QuestionPairCollectionAnswer questionPairCollectionAnswer;
}
