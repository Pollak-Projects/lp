package com.learningpulse.quiz.question.type;

import com.learningpulse.quiz.question.answer.QuestionOrderAnswer;
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
@Table(name = "QUESTION_ORDER_OPTIONS", schema = "QUESTION_ORDER_OPTIONS")
public class QuestionOrderOptions {
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
