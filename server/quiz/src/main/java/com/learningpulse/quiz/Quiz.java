package com.learningpulse.quiz;

// Useful link to understand the difference between @OntToOne and @ManyToOne: https://stackoverflow.com/questions/16119531/hibernate-jpa-manytoone-vs-onetomany
// https://docs.jboss.org/hibernate/annotations/3.5/reference/en/html/entity.html#entity-mapping-association

// TODO add (cascade = CascadeType.ALL, fetch = FetchType.EAGER) to all OneToMany and ManyToOne relationships


import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_file.model.QuestionFile;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_pair.model.QuestionPairCollection;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
public class Quiz {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @CreatedBy
    // TODO change this to User
    private UUID createdBy;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime deadline;

    private boolean viewAfterSubmission;

    // This seems really inefficient @nezsha please advise 2024-09-17

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionText> questionTexts;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionRadio> questionRadios;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionCheckbox> questionCheckboxes;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionFile> questionFiles;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionOrder> questionOrders;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionPairCollection> questionPairCollections;
}
