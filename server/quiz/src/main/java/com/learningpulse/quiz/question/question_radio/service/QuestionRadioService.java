package com.learningpulse.quiz.question.question_radio.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.repository.QuestionRadioRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionRadioService {
    private final QuestionRadioRepository questionRadioRepository;

    public QuestionRadio getQuestionRadioById(UUID id) {
        return questionRadioRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadio not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionRadio> getAllQuestionRadiosByUser(UUID sub) {
        List<QuestionRadio> questionRadios = questionRadioRepository.findAllByCreatedBy(sub);
        if (questionRadios.isEmpty())
            throw new HttpStatusCodeException("QuestionRadio not found", HttpStatus.NOT_FOUND);
        return questionRadios;
    }

    public List<QuestionRadio> getAllQuestionRadios() {
        List<QuestionRadio> questionRadios = questionRadioRepository.findAll();
        if (questionRadios.isEmpty())
            throw new HttpStatusCodeException("QuestionRadio not found", HttpStatus.NOT_FOUND);
        return questionRadios;
    }

    public QuestionRadio createQuestionRadio(UUID sub, @NotNull QuestionRadio questionRadio) {
        questionRadio.setCreatedBy(sub);
        return questionRadioRepository.save(questionRadio);
    }

    public QuestionRadio updateQuestionRadio(@NotNull QuestionRadio questionRadio) {
        return questionRadioRepository.findById(questionRadio.getId())
                .map(q -> questionRadioRepository.save(questionRadio))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadio not found", HttpStatus.NOT_FOUND));
    }

    public QuestionRadio deleteQuestionRadio(UUID id) {
        return questionRadioRepository.findById(id)
                .map(q -> {
                    questionRadioRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadio not found", HttpStatus.NOT_FOUND));
    }
}
