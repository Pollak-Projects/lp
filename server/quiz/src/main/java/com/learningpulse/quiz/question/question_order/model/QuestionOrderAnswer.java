package com.learningpulse.quiz.question.question_order.model;

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
@Table(name = "QUESTION_ORDER_ANSWER", schema = "quiz")
public class QuestionOrderAnswer {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @OneToMany(mappedBy = "questionOrderAnswer")
    private Set<QuestionOrderOptions> questionOrderOptions;

    private int place;

    // TODO replace this with the actual user type
    // FIXME CreatedBy will not work right now because the Auditor haven't yet been tested
    @CreatedBy
    private UUID createdBy;

}
