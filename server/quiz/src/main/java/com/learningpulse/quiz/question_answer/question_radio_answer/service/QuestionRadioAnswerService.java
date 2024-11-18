package com.learningpulse.quiz.question_answer.question_radio_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_answer.QuestionRadioAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_answer.QuestionRadioAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.repository.QuestionRadioAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionRadioAnswerService {
    private final QuestionRadioAnswerRepository questionRadioAnswerRepository;


    public QuestionRadioAnswer getQuestionRadioAnswerById(UUID id) {
        return questionRadioAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NO_CONTENT));
    }

    public List<QuestionRadioAnswer> getAllQuestionRadioAnswerByUser(UUID sub) {
        List<QuestionRadioAnswer> questionRadioAnswers = questionRadioAnswerRepository.findAllByCreatedBy(sub);
        if (questionRadioAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NO_CONTENT);
        return questionRadioAnswers;
    }

    public List<QuestionRadioAnswer> getAllQuestionRadioAnswer() {
        List<QuestionRadioAnswer> questionRadioAnswers = questionRadioAnswerRepository.findAll();
        if (questionRadioAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NO_CONTENT);
        return questionRadioAnswers;
    }

    public QuestionRadioAnswer createQuestionRadioAnswer(UUID sub, @NotNull QuestionRadioAnswerCreateDTO dto) {
        QuestionRadioAnswer questionRadioAnswer = QuestionRadioAnswer.builder()
                .createdBy(sub)
                .belongsTo(QuizAnswer.builder().id(dto.quizAnswerId()).build())
                .questionRadio(QuestionRadio.builder().id(dto.questionRadioId()).build())
                .build();

        questionRadioAnswer.setOptions(dto.options().stream().map(o -> QuestionRadioOptionsAnswer.builder()
                .createdBy(sub)
                .questionRadioAnswer(questionRadioAnswer)
                .questionRadioOptions(QuestionRadioOptions.builder().id(o.questionRadioOptionsId()).build())
                .answer(o.answer())
                .build()).toList());

        return questionRadioAnswerRepository.save(questionRadioAnswer);
    }

    public QuestionRadioAnswer updateQuestionRadioAnswer(@NotNull QuestionRadioAnswerUpdateDTO dto) {
        return questionRadioAnswerRepository.findById(dto.questionRadioAnswerId())
                .map(q -> {
                    if (dto.quizAnswerId() != null)
                        q.setBelongsTo(QuizAnswer.builder().id(dto.quizAnswerId()).build());
                    if (dto.questionRadioId() != null)
                        q.setQuestionRadio(QuestionRadio.builder().id(dto.questionRadioId()).build());
                    if (dto.options() != null && !dto.options().isEmpty()) {
                        q.setOptions(dto.options().stream().map(o -> QuestionRadioOptionsAnswer.builder()
                                .id(o)
                                .build()).toList());
                    }
                    return questionRadioAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NO_CONTENT));
    }

    public QuestionRadioAnswer deleteQuestionRadioAnswer(UUID id) {
        return questionRadioAnswerRepository.findById(id)
                .map(q -> {
                    questionRadioAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NO_CONTENT));
    }
}
