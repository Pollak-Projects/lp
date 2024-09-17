package com.learningpulse.quiz.question.type;

import com.learningpulse.quiz.question.answer.QuestionRadioAnswer;
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
@Table(name = "QUIZ", schema = "QUIZ")
public class QuestionRadioOptions {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private QuestionRadio questionRadio;

    private String title;

    private boolean answer;

    // this is the other side of the relationship defined in QuestionRadioAnswer.java
    @ManyToOne
    private QuestionRadioAnswer questionRadioAnswer;
}
