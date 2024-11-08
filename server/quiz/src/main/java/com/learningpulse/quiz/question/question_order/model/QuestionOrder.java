package com.learningpulse.quiz.question.question_order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderAnswer;
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

    @JsonBackReference("questionOrder-quiz")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    private String title;

    @JsonManagedReference("questionOrderOptions-questionOrder")
    @OneToMany(mappedBy = "questionOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_order_options_id", referencedColumnName = "id")
    private Set<QuestionOrderOptions> options;

    private UUID createdBy;

    @JsonBackReference("selected-questionOrderAnswer")
    @ManyToOne
    private QuestionOrderAnswer questionOrderAnswer;
}
