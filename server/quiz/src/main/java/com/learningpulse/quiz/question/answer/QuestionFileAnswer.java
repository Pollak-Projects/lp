package com.learningpulse.quiz.question.answer;

import com.learningpulse.quiz.question.type.QuestionFile;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
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
@Table(name = "QUESTION_FILE_ANSWER", schema = "QUESTION_FILE_ANSWER")
public class QuestionFileAnswer {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @OneToMany(mappedBy = "answerTo")
    private Set<QuestionFile> answerTo;

    // TODO replace this with the actual user type
    // FIXME CreatedBy will not work right now because the Auditor haven't yet been tested
    @CreatedBy
    private UUID createdBy;

    // TODO replace this with the actual File type
    @OneToMany(mappedBy = "file")
    private UUID file;
}
