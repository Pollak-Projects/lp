package com.learningpulse.question.type;

import com.learningpulse.question.answer.QuestionRadioAnswer;
import com.learningpulse.quiz.Quiz;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_RADIO", schema = "QUESTION_RADIO")
public class QuestionRadio {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Quiz quiz;

    private String title;

    @OneToMany(mappedBy = "questionRadio")
    private Set<QuestionRadioOptions> options;

    // review this @nezsha is this what you meant? 2024-09-17
    @ManyToOne
    private QuestionRadioAnswer questionRadioAnswer;
}
