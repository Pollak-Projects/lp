package com.learningpulse.quiz.quiz_answer;

import com.learningpulse.quiz.question_answer.question_text_answer.model.QuestionTextAnswer;
import com.learningpulse.quiz.quiz.Quiz;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "quiz_answer", schema = "quiz")
public class QuizAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quizId;

    private UUID userId;

    @CreatedDate
    private LocalDateTime createdAt;

    private UUID createdBy;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_text_answer_id", referencedColumnName = "id")
    private List<QuestionTextAnswer> questionTextAnswer;
}
