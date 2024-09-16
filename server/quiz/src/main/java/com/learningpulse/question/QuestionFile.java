package com.learningpulse.question;

import com.learningpulse.quiz.Quiz;
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
@Table(name = "QUESTION_FILE", schema = "QUESTION_FILE")
public class QuestionFile {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Quiz quiz;

    private String title;

    // TODO actually add file entity to somewhere
    // UUID is only a placeholder
    private UUID file;
}
