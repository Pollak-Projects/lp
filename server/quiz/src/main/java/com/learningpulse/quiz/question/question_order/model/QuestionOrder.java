package com.learningpulse.quiz.question.question_order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderAnswer;
import com.learningpulse.quiz.quiz.Quiz;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_ORDER", schema = "quiz")
public class QuestionOrder implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference
    @ManyToOne
    private Quiz quiz;

    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionOrder")
    private Set<QuestionOrderOptions> options;

    private UUID createdBy;

    @JsonBackReference
    @ManyToOne
    private QuestionOrderAnswer questionOrderAnswer;
}
