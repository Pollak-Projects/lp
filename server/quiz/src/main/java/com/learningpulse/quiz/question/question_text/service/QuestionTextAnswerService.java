package com.learningpulse.quiz.question.question_text.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.question.question_text.model.QuestionTextAnswer;
import com.learningpulse.quiz.question.question_text.model.QuestionTextAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionTextAnswerService {
    private final QuestionTextAnswerRepository questionTextAnswerRepository;
    public QuestionTextAnswer getQuestionTextAnswerById(UUID id) {
        return questionTextAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionTextAnswer> getAllQuestionTextAnswersByUser(UUID sub) {
        List<QuestionTextAnswer> questionTextAnswers = questionTextAnswerRepository.findAllByCreatedBy(sub);
        if (questionTextAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NOT_FOUND);
        return questionTextAnswers;
    }

    public List<QuestionTextAnswer> getAllQuestionTextAnswers() {
        List<QuestionTextAnswer> questionTextAnswers = questionTextAnswerRepository.findAll();
        if (questionTextAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NOT_FOUND);
        return questionTextAnswers;
    }

    public List<QuestionTextAnswer> getAllQuestionTextAnswersByQuestion(UUID questionTextId) {
        return questionTextAnswerRepository.findAllByBelongsTo(QuestionText.builder().id(questionTextId).build());
    }

    public QuestionTextAnswer createQuestionTextAnswer(QuestionTextAnswer questionTextAnswer) {
        return questionTextAnswerRepository.save(questionTextAnswer);
    }

    public QuestionTextAnswer updateQuestionTextAnswer(QuestionTextAnswer questionTextAnswer) {
        return questionTextAnswerRepository.findById(questionTextAnswer.getId())
                .map(q -> questionTextAnswerRepository.save(questionTextAnswer))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuestionTextAnswer deleteQuestionTextAnswer(UUID id) {
        return questionTextAnswerRepository.findById(id)
                .map(q -> {
                    questionTextAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NOT_FOUND));
    }
}
