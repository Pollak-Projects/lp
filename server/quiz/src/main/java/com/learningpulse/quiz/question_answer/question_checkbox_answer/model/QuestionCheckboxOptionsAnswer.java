package com.learningpulse.quiz.question_answer.question_checkbox_answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
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
@Table(name = "QUESTION_CHECKBOX_options_ANSWER", schema = "quiz")
public class QuestionCheckboxOptionsAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionCheckboxOptionsAnswer-questionCheckboxAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_checkbox_answer_id", referencedColumnName = "id")
    private QuestionCheckboxAnswer questionCheckboxAnswer;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_checkbox_options_answer_to_id", referencedColumnName = "id")
    private QuestionCheckboxOptions questionCheckboxOptions;

    private Boolean answer;

    private UUID createdBy;
}
