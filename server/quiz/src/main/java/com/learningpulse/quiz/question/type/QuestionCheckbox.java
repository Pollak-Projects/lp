package com.learningpulse.quiz.question.type;

import com.learningpulse.quiz.question.answer.QuestionCheckBoxAnswer;
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
@Table(name = "QUESTION_CHECKBOX", schema = "QUESTION_CHECKBOX")
public class QuestionCheckbox {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Quiz quiz;

    private String title;

    @OneToMany(mappedBy = "questionCheckbox")
    private Set<QuestionCheckboxOptions> options;


    @ManyToOne
    private QuestionCheckBoxAnswer questionCheckBoxAnswer;
}
