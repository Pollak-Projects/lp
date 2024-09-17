package com.learningpulse.question.type;

import com.learningpulse.question.answer.QuestionTextAnswer;
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
@Table(name = "QUESTION_TEXT", schema = "QUESTION_TEXT")
public class QuestionText {
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
