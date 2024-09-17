package com.learningpulse.quiz.question.answer;

import com.learningpulse.quiz.question.type.QuestionText;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_TEXT_ANSWER", schema = "QUESTION_TEXT_ANSWER")
public class QuestionTextAnswer {
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
