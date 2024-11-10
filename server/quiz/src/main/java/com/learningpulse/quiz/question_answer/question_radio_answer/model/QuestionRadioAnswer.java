package com.learningpulse.quiz.question_answer.question_radio_answer.model;

import com.fasterxml.jackson.annotation.*;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.List;
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

    @JsonBackReference("questionRadioAnswer-quizAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_answer_id", referencedColumnName = "id")
    private QuizAnswer belongsTo;

    @JsonManagedReference("questionRadioOptionsAnswer-questionRadioAnswer")
    @OneToMany(mappedBy = "questionRadioAnswer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QuestionRadioOptionsAnswer> options;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_radio_answer_to_id", referencedColumnName = "id")
    private QuestionRadio questionRadio;

    private UUID createdBy;
}
