package com.learningpulse.quiz.question_answer.question_order_answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
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
@Table(name = "QUESTION_ORDER_options_answer", schema = "quiz")
public class QuestionOrderOptionsAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionOrderOptionsAnswer-questionOrderAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_order_answer_id", referencedColumnName = "id")
    private QuestionOrderAnswer questionOrderAnswer;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_order_options_answer_to_id", referencedColumnName = "id")
    private QuestionOrderOptions questionOrderOptions;

    private Integer place;

    private UUID createdBy;
}
