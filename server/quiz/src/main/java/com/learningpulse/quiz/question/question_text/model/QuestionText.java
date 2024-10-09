package com.learningpulse.quiz.question.question_text.model;

import com.learningpulse.quiz.Quiz;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "belongsTo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionTextAnswer> questionTextAnswers;

    private UUID createdBy;

    @CreatedDate
    private LocalDateTime createdAt;
}
