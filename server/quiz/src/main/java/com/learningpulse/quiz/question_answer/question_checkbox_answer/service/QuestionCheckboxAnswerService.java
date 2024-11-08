package com.learningpulse.quiz.question_answer.question_checkbox_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionCheckboxAnswerService {
    private final QuestionCheckboxAnswerRepository questionCheckboxAnswerRepository;


    public QuestionCheckboxAnswer getQuestionCheckboxAnswerById(UUID id) {
        return questionCheckboxAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionCheckboxAnswer> getAllQuestionCheckboxAnswerByUser(UUID sub) {
        List<QuestionCheckboxAnswer> questionCheckboxAnswers = questionCheckboxAnswerRepository.findAllByCreatedBy(sub);
        if (questionCheckboxAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NOT_FOUND);
        return questionCheckboxAnswers;
    }

    public List<QuestionCheckboxAnswer> getAllQuestionCheckboxAnswer() {
        List<QuestionCheckboxAnswer> questionCheckboxAnswers = questionCheckboxAnswerRepository.findAll();
        if (questionCheckboxAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NOT_FOUND);
        return questionCheckboxAnswers;
    }

    public QuestionCheckboxAnswer createQuestionCheckboxAnswer(QuestionCheckboxAnswer questionCheckboxAnswer) {
        return questionCheckboxAnswerRepository.save(questionCheckboxAnswer);
    }

    public QuestionCheckboxAnswer updateQuestionCheckboxAnswer(@NotNull QuestionCheckboxAnswer questionCheckboxAnswer) {
        return questionCheckboxAnswerRepository.findById(questionCheckboxAnswer.getId())
                .map(q -> questionCheckboxAnswerRepository.save(questionCheckboxAnswer))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuestionCheckboxAnswer deleteQuestionCheckboxAnswer(UUID id) {
        return questionCheckboxAnswerRepository.findById(id)
                .map(q -> {
                    questionCheckboxAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NOT_FOUND));
    }
}
