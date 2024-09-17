package com.learningpulse.question.answer;

import com.learningpulse.question.type.QuestionCheckbox;
import com.learningpulse.question.type.QuestionCheckboxOptions;
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
@Table(name = "QUESTION_CHECKBOX_ANSWER", schema = "QUESTION_CHECKBOX_ANSWER")
public class QuestionCheckBoxAnswer {
    @Id
    @GeneratedValue
    // Isn't defined in the database diagram
    private UUID id;

    @OneToMany(mappedBy = "questionCheckBoxAnswer")
    private Set<QuestionCheckboxOptions> questionCheckboxOptions;

    // TODO replace this with the actual user type
    // FIXME CreatedBy will not work right now because the Auditor haven't yet been tested
    @CreatedBy
    private UUID createdBy;

    // please review this @nezsha 2024-09-17
    // should these relationships be uni or bidirectional? im not sure.
    // there's no relationship defined between QuestionCheckBox and QuestionCheckBoxAnswer in the database diagram
    // so im just guessing
    @OneToMany(mappedBy = "questionCheckBoxAnswer")
    private Set<QuestionCheckbox> questionCheckbox;
}
