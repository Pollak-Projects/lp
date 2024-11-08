package com.learningpulse.quiz.question_answer.question_radio_answer.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
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
@Table(name = "QUESTION_RADIO_ANSWER", schema = "quiz")
public class QuestionRadioAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonManagedReference("questionRadioAnswer-questionRadio")
    @OneToMany(mappedBy = "questionRadioAnswer")
    private Set<QuestionRadio> questionRadios;

    private UUID createdBy;

    @JsonManagedReference("questionRadioAnswer-selected")
    @OneToMany(mappedBy = "questionRadioAnswer")
    private Set<QuestionRadioOptions> selected;

}
