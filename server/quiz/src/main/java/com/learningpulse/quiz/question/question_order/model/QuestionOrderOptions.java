package com.learningpulse.quiz.question.question_order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderAnswer;
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

    @JsonBackReference("questionOrderOptions-questionOrder")
    @ManyToOne
    private QuestionOrder questionOrder;

    private String title;

    private int place;

    private UUID createdBy;

    @JsonBackReference("selected-questionOrderAnswer")
    @ManyToOne
    private QuestionOrderAnswer questionOrderAnswer;
}
