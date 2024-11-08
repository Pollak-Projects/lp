package com.learningpulse.quiz.question.question_checkbox.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
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

    @JsonBackReference("questionCheckboxOptions-questionCheckbox")
    @ManyToOne
    private QuestionCheckbox questionCheckbox;

    private String name;

    private boolean answer;

    private UUID createdBy;

    @JsonBackReference("questionCheckboxAnswer-questionCheckboxOptions")
    @ManyToOne
    private QuestionCheckboxAnswer questionCheckboxAnswer;
}
