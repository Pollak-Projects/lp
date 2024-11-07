package com.learningpulse.quiz.question.question_order.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "QUESTION_ORDER_ANSWER", schema = "quiz")
public class QuestionOrderAnswer implements Serializable {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    private int place;

    private UUID createdBy;

    @JsonManagedReference("selected-questionOrderAnswer")
    @OneToMany(mappedBy = "questionOrderAnswer")
    private Set<QuestionOrderOptions> selected;

}
