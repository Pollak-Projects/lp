package com.learningpulse.quiz.question_answer.question_order_answer.model;

import com.fasterxml.jackson.annotation.*;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
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
@Table(name = "QUESTION_ORDER_ANSWER", schema = "quiz")
public class QuestionOrderAnswer implements Serializable {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @JsonBackReference("questionOrderAnswer-quizAnswer")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_answer_id", referencedColumnName = "id")
    private QuizAnswer belongsTo;

    @JsonManagedReference("questionOrderOptionsAnswer-questionOrderAnswer")
    @OneToMany(mappedBy = "questionOrderAnswer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<QuestionOrderOptionsAnswer> options;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "question_order_answer_to_id", referencedColumnName = "id")
    private QuestionOrder questionOrder;

    private UUID createdBy;
}
