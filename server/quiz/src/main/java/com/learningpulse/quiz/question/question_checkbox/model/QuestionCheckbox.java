package com.learningpulse.quiz.question.question_checkbox.model;

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
@Table(name = "QUESTION_CHECKBOX", schema = "quiz")
public class QuestionCheckbox implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Quiz quiz;

    private String title;

    @OneToMany(mappedBy = "questionCheckbox")
    private Set<QuestionCheckboxOptions> options;


    @ManyToOne
    private QuestionCheckboxAnswer questionCheckBoxAnswer;
}
