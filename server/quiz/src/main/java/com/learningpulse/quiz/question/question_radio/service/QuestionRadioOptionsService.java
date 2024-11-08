package com.learningpulse.quiz.question.question_radio.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question.question_radio.repository.QuestionRadioOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionRadioOptionsService {
    private final QuestionRadioOptionsRepository questionRadioOptionsRepository;

    public QuestionRadioOptions getQuestionRadioOptionsById(UUID id) {
        return questionRadioOptionsRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioOptions not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionRadioOptions> getAllQuestionRadioOptionsByUser(UUID sub) {
        List<QuestionRadioOptions> questionRadioOptions = questionRadioOptionsRepository.findAllByCreatedBy(sub);
        if (questionRadioOptions.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioOptions not found", HttpStatus.NOT_FOUND);
        return questionRadioOptions;
    }

    public List<QuestionRadioOptions> getAllQuestionRadioOptions() {
        List<QuestionRadioOptions> questionRadioOptions = questionRadioOptionsRepository.findAll();
        if (questionRadioOptions.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioOptions not found", HttpStatus.NOT_FOUND);
        return questionRadioOptions;
    }

    public QuestionRadioOptions createQuestionRadioOptions(UUID sub, @NotNull QuestionRadioOptions questionRadioOptions) {
        questionRadioOptions.setCreatedBy(sub);
        return questionRadioOptionsRepository.save(questionRadioOptions);
    }

    public QuestionRadioOptions updateQuestionRadioOptions(@NotNull QuestionRadioOptions questionRadioOptions) {
        return questionRadioOptionsRepository.findById(questionRadioOptions.getId())
                .map(q -> questionRadioOptionsRepository.save(questionRadioOptions))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioOptions not found", HttpStatus.NOT_FOUND));
    }

    public QuestionRadioOptions deleteQuestionRadioOptions(UUID id) {
        return questionRadioOptionsRepository.findById(id)
                .map(q -> {
                    questionRadioOptionsRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioOptions not found", HttpStatus.NOT_FOUND));

    }
}
