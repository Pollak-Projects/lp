package com.learningpulse.quiz.question_answer.question_radio_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswer;
import com.learningpulse.quiz.question_answer.question_radio_answer.model.QuestionRadioAnswerRepository;
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
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionRadioAnswer> getAllQuestionRadioAnswerByUser(UUID sub) {
        List<QuestionRadioAnswer> questionRadioAnswers = questionRadioAnswerRepository.findAllByCreatedBy(sub);
        if (questionRadioAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NOT_FOUND);
        return questionRadioAnswers;
    }

    public List<QuestionRadioAnswer> getAllQuestionRadioAnswer() {
        List<QuestionRadioAnswer> questionRadioAnswers = questionRadioAnswerRepository.findAll();
        if (questionRadioAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NOT_FOUND);
        return questionRadioAnswers;
    }

    public QuestionRadioAnswer createQuestionRadioAnswer(QuestionRadioAnswer questionRadioAnswer) {
        return questionRadioAnswerRepository.save(questionRadioAnswer);
    }

    public QuestionRadioAnswer updateQuestionRadioAnswer(@NotNull QuestionRadioAnswer questionRadioAnswer) {
        return questionRadioAnswerRepository.findById(questionRadioAnswer.getId())
                .map(q -> questionRadioAnswerRepository.save(questionRadioAnswer))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuestionRadioAnswer deleteQuestionRadioAnswer(UUID id) {
        return questionRadioAnswerRepository.findById(id)
                .map(q -> {
                    questionRadioAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionRadioAnswer not found", HttpStatus.NOT_FOUND));
    }
}
