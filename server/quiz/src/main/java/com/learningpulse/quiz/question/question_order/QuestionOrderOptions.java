package com.learningpulse.quiz.question.question_order;

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
@Table(name = "QUESTION_ORDER_OPTIONS", schema = "quiz")
public class QuestionOrderOptions implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private QuestionOrder questionOrder;

    private String title;

    private int place;

    @ManyToOne
    private QuestionOrderAnswer questionOrderAnswer;
}
