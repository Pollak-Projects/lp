package com.learningpulse.quiz.question.question_radio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "questionRadio")
    private Set<QuestionRadioOptions> options;

    private UUID createdBy;

    @JsonBackReference
    @ManyToOne
    private QuestionRadioAnswer questionRadioAnswer;
}
