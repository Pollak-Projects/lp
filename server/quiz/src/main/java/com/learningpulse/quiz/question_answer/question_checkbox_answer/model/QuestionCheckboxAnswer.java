package com.learningpulse.quiz.question_answer.question_checkbox_answer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
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
@Table(name = "QUESTION_CHECKBOX_ANSWER", schema = "quiz")
public class QuestionCheckboxAnswer implements Serializable {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @JsonBackReference("questionCheckboxAnswer-quizAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_answer_id", referencedColumnName = "id")
    private QuizAnswer belongsTo;

    @JsonManagedReference("questionCheckboxOptionsAnswer-questionCheckboxAnswer")
    @OneToMany(mappedBy = "questionCheckboxAnswer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QuestionCheckboxOptionsAnswer> options;

    @JsonManagedReference("questionCheckbox-questionCheckboxAnswer")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn(name = "question_checkbox_id", referencedColumnName = "id")
    private QuestionCheckbox questionCheckbox;

    private UUID createdBy;
}
