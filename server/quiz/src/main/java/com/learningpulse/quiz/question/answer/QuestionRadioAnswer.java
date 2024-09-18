package com.learningpulse.quiz.question.answer;

import com.learningpulse.quiz.question.type.QuestionRadio;
import com.learningpulse.quiz.question.type.QuestionRadioOptions;
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
@Table(name = "QUESTION_RADIO_ANSWER", schema = "quiz")
public class QuestionRadioAnswer {
    @Id
    @GeneratedValue
    // This isn't in the database diagram
    private UUID id;

    // review this @nezsha is this what you meant? 2024-09-17
    // The database diagram is unclear about the relationship between QuestionRadioAnswer and QuestionRadio
    @OneToMany(mappedBy = "questionRadioAnswer")
    private Set<QuestionRadio> questionRadios;

    // TODO replace this with the actual user type
    // FIXME CreatedBy will not work right now because the Auditor haven't yet been tested
    @CreatedBy
    private UUID createdBy;

    // TODO complete this
    // ask @nezsha about what is this for
    @OneToMany(mappedBy = "questionRadioAnswer")
    private Set<QuestionRadioOptions> selected;

}
