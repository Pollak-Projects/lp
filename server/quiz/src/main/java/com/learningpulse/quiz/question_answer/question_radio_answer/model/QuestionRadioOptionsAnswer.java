package com.learningpulse.quiz.question_answer.question_radio_answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
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
@Table(name = "QUESTION_RADIO_options_ANSWER", schema = "quiz")
public class QuestionRadioOptionsAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionRadioOptionsAnswer-questionRadioAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_radio_answer_id", referencedColumnName = "id")
    private QuestionRadioAnswer questionRadioAnswer;

    private String answer;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_radio_options_answer_to_id", referencedColumnName = "id")
    private QuestionRadioOptions questionRadioOptions;

    private UUID createdBy;
}
