package com.learningpulse.quiz.question.question_order.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderAnswerRepository;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderRepository;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionOrderAnswerService {
    private final QuestionOrderAnswerRepository questionOrderAnswerRepository;

    public QuestionOrderAnswer getQuestionOrderAnswerById(UUID id){
        return questionOrderAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionOrderAnswer> getAllQuestionOrderAnswerByUser(UUID sub) {
        List<QuestionOrderAnswer> questionOrderAnswers = questionOrderAnswerRepository.findAllByCreatedBy(sub);
        if (questionOrderAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NOT_FOUND);
        return questionOrderAnswers;
    }

    public List<QuestionOrderAnswer> getAllQuestionOrderAnswer() {
        List<QuestionOrderAnswer> questionOrderAnswers = questionOrderAnswerRepository.findAll();
        if (questionOrderAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NOT_FOUND);
        return questionOrderAnswers;
    }

    public QuestionOrderAnswer createQuestionOrderAnswer(QuestionOrderAnswer questionOrderAnswer) {
        return questionOrderAnswerRepository.save(questionOrderAnswer);
    }

    public QuestionOrderAnswer updateQuestionOrderAnswer(@NotNull QuestionOrderAnswer questionOrderAnswer) {
        return questionOrderAnswerRepository.findById(questionOrderAnswer.getId())
                .map(q -> questionOrderAnswerRepository.save(questionOrderAnswer))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuestionOrderAnswer deleteQuestionOrderAnswer(UUID id) {
        return questionOrderAnswerRepository.findById(id)
                .map(q -> {
                    questionOrderAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NOT_FOUND));
    }
}
