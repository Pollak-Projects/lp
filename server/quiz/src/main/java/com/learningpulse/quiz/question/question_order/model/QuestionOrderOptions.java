package com.learningpulse.quiz.question.question_order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_order_id", referencedColumnName = "id")
    private QuestionOrder questionOrder;

    private String title;

    private Integer place;

    private UUID createdBy;
}
