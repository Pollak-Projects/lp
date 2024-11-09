package com.learningpulse.quiz.quiz_answer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
import com.learningpulse.quiz.question_answer.question_file_answer.model.QuestionFileAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question_answer.question_pair_answer.model.QuestionPairCollectionAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
import com.learningpulse.quiz.question_answer.question_text_answer.model.QuestionTextAnswer;
import com.learningpulse.quiz.quiz.Quiz;
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
@Table(name = "quiz_answer", schema = "quiz")
public class QuizAnswer implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    @JsonBackReference("quizAnswer-quiz")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private Quiz quiz;

    @CreatedDate
    private LocalDateTime createdAt;

    private UUID createdBy;

    @JsonManagedReference("questionTextAnswer-quizAnswer")
    @OneToMany(mappedBy = "belongsToa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionTextAnswer> questionTextAnswers;

    @JsonManagedReference("questionRadioAnswer-quizAnswer")
    @OneToMany(mappedBy = "belongsTo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionRadioAnswer> questionRadioAnswers;

    @JsonManagedReference("questionCheckboxAnswer-quizAnswer")
    @OneToMany(mappedBy = "belongsTo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionCheckboxAnswer> questionCheckboxAnswers;

    @JsonManagedReference("questionFileAnswer-quizAnswer")
    @OneToMany(mappedBy = "belongsTo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionFileAnswer> questionFileAnswers;

    @JsonManagedReference("questionOrderAnswer-quizAnswer")
    @OneToMany(mappedBy = "belongsTo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionOrderAnswer> questionOrderAnswers;

    @JsonManagedReference("questionPairCollectionAnswer-quizAnswer")
    @OneToMany(mappedBy = "belongsTo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionPairCollectionAnswer> questionPairCollectionAnswers;
}
