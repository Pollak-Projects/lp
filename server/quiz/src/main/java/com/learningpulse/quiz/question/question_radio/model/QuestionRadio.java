package com.learningpulse.quiz.question.question_radio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "QUESTION_RADIO", schema = "quiz")
public class QuestionRadio implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference
    @ManyToOne
    private Quiz quiz;

    private String title;

    @OneToMany(mappedBy = "questionRadio")
    private Set<QuestionRadioOptions> options;

    // review this @nezsha is this what you meant? 2024-09-17
    @ManyToOne
    private QuestionRadioAnswer questionRadioAnswer;
}
