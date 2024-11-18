package com.learningpulse.quiz.question_answer.question_text_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question_answer.question_text_answer.dto.question_text_answer.QuestionTextAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_text_answer.dto.question_text_answer.QuestionTextAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_text_answer.model.QuestionTextAnswer;
import com.learningpulse.quiz.question_answer.question_text_answer.repository.QuestionTextAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
                .orElseThrow(() -> new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NO_CONTENT));
    }

    public List<QuestionTextAnswer> getAllQuestionTextAnswersByUser(UUID sub) {
        List<QuestionTextAnswer> questionTextAnswers = questionTextAnswerRepository.findAllByCreatedBy(sub);
        if (questionTextAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NO_CONTENT);
        return questionTextAnswers;
    }

    public List<QuestionTextAnswer> getAllQuestionTextAnswers() {
        List<QuestionTextAnswer> questionTextAnswers = questionTextAnswerRepository.findAll();
        if (questionTextAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NO_CONTENT);
        return questionTextAnswers;
    }

    public List<QuestionTextAnswer> getAllQuestionTextAnswersByQuestion(UUID questionTextId) {
        List<QuestionTextAnswer> questionTextAnswers = questionTextAnswerRepository.findAllByBelongsToId(questionTextId);
        if (questionTextAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NO_CONTENT);
        return questionTextAnswers;
    }

    public QuestionTextAnswer createQuestionTextAnswer(UUID sub, @NotNull QuestionTextAnswerCreateDTO questionTextAnswerDTO) {
        QuestionTextAnswer questionTextAnswer = QuestionTextAnswer.builder()
                .createdBy(sub)
                .belongsTo(QuizAnswer.builder().id(questionTextAnswerDTO.quizAnswerId()).build())
                .answer(questionTextAnswerDTO.answer())
                .build();
        return questionTextAnswerRepository.save(questionTextAnswer);
    }

    public QuestionTextAnswer updateQuestionTextAnswer(@NotNull QuestionTextAnswerUpdateDTO questionTextAnswerDTO) {
        return questionTextAnswerRepository.findById(questionTextAnswerDTO.questionTextId())
                .map(q -> {
                    if (questionTextAnswerDTO.quizAnswerId() != null)
                        q.setBelongsTo(QuizAnswer.builder().id(questionTextAnswerDTO.quizAnswerId()).build());
                    if (questionTextAnswerDTO.answer() != null)
                        q.setAnswer(questionTextAnswerDTO.answer());
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NO_CONTENT));
    }

    public QuestionTextAnswer deleteQuestionTextAnswer(UUID id) {
        return questionTextAnswerRepository.findById(id)
                .map(q -> {
                    questionTextAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionTextAnswer not found", HttpStatus.NO_CONTENT));
    }
}
