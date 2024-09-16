package com.learningpulse.question;

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
@Table(name = "QUESTION_CHECKBOX_OPTIONS", schema = "QUESTION_CHECKBOX_OPTIONS")
public class QuestionCheckboxOptions {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private QuestionCheckbox questionCheckbox;

    private String name;

    private boolean answer;
}
