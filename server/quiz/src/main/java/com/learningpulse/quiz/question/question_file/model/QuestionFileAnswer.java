package com.learningpulse.quiz.question.question_file.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "question_file_answer", schema = "quiz")
public class QuestionFileAnswer implements Serializable {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @JsonManagedReference("questionFile-answerTo")
    @OneToMany(mappedBy = "questionFileAnswers")
    private Set<QuestionFile> answerTo;

    private UUID createdBy;

    // TODO replace this with the actual File type
    private UUID file;
}
