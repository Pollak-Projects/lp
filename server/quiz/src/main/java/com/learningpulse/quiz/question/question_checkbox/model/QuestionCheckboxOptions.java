package com.learningpulse.quiz.question.question_checkbox.model;

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
@Table(name = "QUESTION_CHECKBOX_OPTIONS", schema = "quiz")
public class QuestionCheckboxOptions implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference
    @ManyToOne
    private QuestionCheckbox questionCheckbox;

    private String name;

    private boolean answer;

    @JsonBackReference
    @ManyToOne
    private QuestionCheckboxAnswer questionCheckBoxAnswer;
}
