package com.learningpulse.quiz.question.question_checkbox.model;

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
@Table(name = "QUESTION_CHECKBOX_ANSWER", schema = "quiz")
public class QuestionCheckboxAnswer implements Serializable {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionCheckboxAnswer")
    private Set<QuestionCheckboxOptions> questionCheckboxOptions;

    private UUID createdBy;

    @JsonManagedReference
    @OneToMany(mappedBy = "questionCheckboxAnswer")
    private Set<QuestionCheckbox> questionCheckbox;
}
