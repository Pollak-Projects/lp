package com.learningpulse.quiz.question.type;

import com.learningpulse.quiz.Quiz;
import com.learningpulse.quiz.question.answer.QuestionTextAnswer;
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
@Table(name = "QUESTION_TEXT", schema = "quiz")
public class QuestionText implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Quiz quiz;

    private String title;

    private String answer;

    @OneToMany(mappedBy = "belongsTo")
    private Set<QuestionTextAnswer> questionTextAnswers;
}
