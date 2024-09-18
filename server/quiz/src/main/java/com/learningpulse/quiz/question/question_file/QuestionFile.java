package com.learningpulse.quiz.question.question_file;

import com.learningpulse.quiz.Quiz;
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
@Table(name = "question_file", schema = "quiz")
public class QuestionFile implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Quiz quiz;

    private String title;

    // TODO actually add file entity to somewhere
    // UUID is only a placeholder
    private UUID file;
    @ManyToOne(optional = false)
    private QuestionFileAnswer questionFileAnswers;
}
