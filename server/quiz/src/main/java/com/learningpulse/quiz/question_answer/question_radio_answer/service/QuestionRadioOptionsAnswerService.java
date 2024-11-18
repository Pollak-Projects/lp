package com.learningpulse.quiz.question_answer.question_radio_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadio;
import com.learningpulse.quiz.question.question_radio.model.QuestionRadioOptions;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_options_answer.QuestionRadioOptionsAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.dto.question_radio_options_answer.QuestionRadioOptionsAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.repository.QuestionRadioOptionsAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionRadioOptionsAnswerService {
    private final QuestionRadioOptionsAnswerRepository questionRadioOptionsAnswerRepository;


    public QuestionRadioOptionsAnswer getQuestionRadioOptionsAnswerById(UUID id) {
        return questionRadioOptionsAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioOptionsAnswer not found", HttpStatus.NO_CONTENT));
    }

    public List<QuestionRadioOptionsAnswer> getAllQuestionRadioOptionsAnswerByUser(UUID sub) {
        List<QuestionRadioOptionsAnswer> questionRadioOptionsAnswers = questionRadioOptionsAnswerRepository.findAllByCreatedBy(sub);
        if (questionRadioOptionsAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioOptionsAnswer not found", HttpStatus.NO_CONTENT);
        return questionRadioOptionsAnswers;
    }

    public List<QuestionRadioOptionsAnswer> getAllQuestionRadioOptionsAnswer() {
        List<QuestionRadioOptionsAnswer> questionRadioOptionsAnswers = questionRadioOptionsAnswerRepository.findAll();
        if (questionRadioOptionsAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioOptionsAnswer not found", HttpStatus.NO_CONTENT);
        return questionRadioOptionsAnswers;
    }

    public QuestionRadioOptionsAnswer createQuestionRadioOptionsAnswer(UUID sub, @NotNull QuestionRadioOptionsAnswerCreateDTO dto) {
        QuestionRadioOptionsAnswer questionRadioOptionsAnswer = QuestionRadioOptionsAnswer.builder()
                .createdBy(sub)
                .questionRadioAnswer(QuestionRadioAnswer.builder().id(dto.questionRadioAnswerId()).build())
                .questionRadioOptions(QuestionRadioOptions.builder().id(dto.questionRadioOptionsId()).build())
                .answer(dto.answer())
                .build();
        return questionRadioOptionsAnswerRepository.save(questionRadioOptionsAnswer);
    }

    public QuestionRadioOptionsAnswer updateQuestionRadioOptionsAnswer(@NotNull QuestionRadioOptionsAnswerUpdateDTO dto) {
        return questionRadioOptionsAnswerRepository.findById(dto.questionRadioOptionsAnswerId())
                .map(q -> {
                    if (dto.questionRadioAnswerId() != null)
                        q.setQuestionRadioAnswer(QuestionRadioAnswer.builder().id(dto.questionRadioAnswerId()).build());
                    if (dto.questionRadioOptionsId() != null)
                        q.setQuestionRadioOptions(QuestionRadioOptions.builder().id(dto.questionRadioOptionsId()).build());
                    if (dto.answer() != null)
                        q.setAnswer(dto.answer());
                    return questionRadioOptionsAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioOptionsAnswer not found", HttpStatus.NO_CONTENT));
    }

    public QuestionRadioOptionsAnswer deleteQuestionRadioOptionsAnswer(UUID id) {
        return questionRadioOptionsAnswerRepository.findById(id)
                .map(q -> {
                    questionRadioOptionsAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioOptionsAnswer not found", HttpStatus.NO_CONTENT));
    }
}
