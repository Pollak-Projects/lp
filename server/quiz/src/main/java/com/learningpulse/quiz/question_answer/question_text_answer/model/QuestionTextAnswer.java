package com.learningpulse.quiz.question_answer.question_text_answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "QUESTION_TEXT_ANSWER", schema = "quiz")
public class  QuestionTextAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("questionTextAnswer-quizAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_answer_id", referencedColumnName = "id")
    private QuizAnswer belongsTo;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_text_answer_to_id", referencedColumnName = "id")
    private QuestionText questionText;

    private String answer;

    private UUID createdBy;

    @CreatedDate
    private LocalDateTime createdAt;
}
