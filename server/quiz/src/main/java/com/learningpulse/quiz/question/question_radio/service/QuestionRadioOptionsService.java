package com.learningpulse.quiz.question.question_radio.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_radio.dto.question_radio_options.QuestionRadioOptionsCreateDTO;
import com.learningpulse.quiz.question.question_radio.dto.question_radio_options.QuestionRadioOptionsUpdateDTO;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question.question_radio.repository.QuestionRadioOptionsRepository;
import jakarta.transaction.Transactional;
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

    public QuestionRadioOptions createQuestionRadioOptions(UUID sub, @NotNull QuestionRadioOptionsCreateDTO dto) {
        QuestionRadioOptions questionRadioOptions = QuestionRadioOptions.builder()
                .createdBy(sub)
                .questionRadio(QuestionRadio.builder().id(dto.questionRadioId()).build())
                .title(dto.title())
                .answer(dto.answer())
                .build();
        return questionRadioOptionsRepository.save(questionRadioOptions);
    }

    // FIXME if answer is not defined in the request, it will be set to as false
    @Transactional
    public QuestionRadioOptions updateQuestionRadioOptions(@NotNull QuestionRadioOptionsUpdateDTO dto) {
        return questionRadioOptionsRepository.findById(dto.questionRadioOptionsId())
                .map(q -> {
                    if (dto.title() != null)
                        q.setTitle(dto.title());
                    q.setAnswer(dto.answer());
                    return questionRadioOptionsRepository.save(q);
                })
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
