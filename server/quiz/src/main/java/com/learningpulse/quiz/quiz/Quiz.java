package com.learningpulse.quiz.quiz;

// Useful link to understand the difference between @OntToOne and @ManyToOne: https://stackoverflow.com/questions/16119531/hibernate-jpa-manytoone-vs-onetomany
// https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html#entity-mapping-association

// TODO add (cascade = CascadeType.ALL, fetch = FetchType.EAGER) to all OneToMany and ManyToOne relationships


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_file.model.QuestionFile;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
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
@Table(name = "QUIZ", schema = "quiz")
public class Quiz implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private UUID createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime deadline;

    private boolean viewAfterSubmission;

    @JsonManagedReference("questionText-quiz")
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionText> questionTexts;

    @JsonManagedReference("questionRadio-quiz")
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionRadio> questionRadios;

    @JsonManagedReference("questionCheckbox-quiz")
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionCheckbox> questionCheckboxes;

    @JsonManagedReference("questionFile-quiz")
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionFile> questionFiles;

    @JsonManagedReference("questionOrder-quiz")
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionOrder> questionOrders;

    @JsonManagedReference("questionPairCollection-quiz")
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<QuestionPairCollection> questionPairCollections;

    @JsonManagedReference("quizAnswer-quiz")
    @OneToOne(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private QuizAnswer quizAnswer;
}
