package com.learningpulse.quiz.question.question_radio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
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
@Table(name = "QUESTION_RADIO_OPTIONS", schema = "quiz")
public class QuestionRadioOptions implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionRadioOptions-questionRadio")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_radio_id", referencedColumnName = "id")
    private QuestionRadio questionRadio;

    private String title;

    private boolean answer;

    private UUID createdBy;

    // TODO REMOVE THESE
    // this is the other side of the relationship defined in QuestionRadioAnswer.java
    @JsonBackReference("questionRadioAnswer-selected")
    @ManyToOne
    private QuestionRadioAnswer questionRadioAnswer;
}
