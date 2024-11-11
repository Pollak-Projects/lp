package com.learningpulse.quiz.question.question_radio.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_radio.dto.question_radio.QuestionRadioCreateDTO;
import com.learningpulse.quiz.question.question_radio.dto.question_radio.QuestionRadioUpdateDTO;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question.question_radio.repository.QuestionRadioRepository;
import com.learningpulse.quiz.quiz.Quiz;
import jakarta.transaction.Transactional;
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

    public QuestionRadio createQuestionRadio(UUID sub, @NotNull QuestionRadioCreateDTO dto) {
        QuestionRadio questionRadio = QuestionRadio.builder()
                .createdBy(sub)
                .quiz(Quiz.builder().id(dto.quizId()).build())
                .title(dto.title())
                .build();

        questionRadio.setOptions(dto.options().stream().map(o -> QuestionRadioOptions.builder()
                .createdBy(sub)
                .questionRadio(questionRadio)
                .title(o.title())
                .answer(o.answer())
                .build()).toList());

        return questionRadioRepository.save(questionRadio);
    }

    @Transactional
    public QuestionRadio updateQuestionRadio(@NotNull QuestionRadioUpdateDTO dto) {
        return questionRadioRepository.findById(dto.questionRadioId())
                .map(q -> {
                    if (dto.quizId() != null)
                        q.setQuiz(Quiz.builder().id(dto.quizId()).build());
                    if (dto.title() != null)
                        q.setTitle(dto.title());
                    if (dto.options() != null && !dto.options().isEmpty())
                        q.setOptions(dto.options().stream().map(o -> QuestionRadioOptions.builder()
                                .id(o)
                                .build()).toList());
                    return questionRadioRepository.save(q);
                })
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
