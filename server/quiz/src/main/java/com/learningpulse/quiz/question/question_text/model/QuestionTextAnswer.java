package com.learningpulse.quiz.question.question_text.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
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
@Table(name = "QUESTION_TEXT_ANSWER", schema = "quiz")
public class QuestionTextAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private QuestionText belongsTo;

    private String answer;

    // TODO replace this with the actual user type
    // FIXME @CreatedBy will not work right now because the Auditor haven't yet been tested
    @CreatedBy
    private UUID createdBy;
}
