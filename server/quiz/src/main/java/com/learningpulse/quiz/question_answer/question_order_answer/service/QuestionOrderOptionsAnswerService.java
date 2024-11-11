package com.learningpulse.quiz.question_answer.question_order_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_options_answer.QuestionOrderOptionsAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_options_answer.QuestionOrderOptionsAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.repository.QuestionOrderOptionsAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionOrderOptionsAnswerService {
    private final QuestionOrderOptionsAnswerRepository questionOrderOptionsAnswerRepository;

    public QuestionOrderOptionsAnswer getQuestionOrderOptionsAnswerById(UUID id) {
        return questionOrderOptionsAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderOptionsAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionOrderOptionsAnswer> getAllQuestionOrderOptionsAnswer() {
        List<QuestionOrderOptionsAnswer> questionOrderOptionsAnswers = questionOrderOptionsAnswerRepository.findAll();
        if (questionOrderOptionsAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderOptionsAnswer not found", HttpStatus.NOT_FOUND);
        return questionOrderOptionsAnswers;
    }

    public List<QuestionOrderOptionsAnswer> getAllQuestionOrderOptionsAnswerByUser(UUID sub) {
        List<QuestionOrderOptionsAnswer> questionOrderOptionsAnswers = questionOrderOptionsAnswerRepository.findAllByCreatedBy(sub);
        if (questionOrderOptionsAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderOptionsAnswer not found", HttpStatus.NOT_FOUND);
        return questionOrderOptionsAnswers;
    }

    public QuestionOrderOptionsAnswer createQuestionOrderOptionsAnswer(UUID sub, @NotNull QuestionOrderOptionsAnswerCreateDTO dto) {
        QuestionOrderOptionsAnswer questionOrderOptionsAnswer = QuestionOrderOptionsAnswer.builder()
                .createdBy(sub)
                .questionOrderAnswer(QuestionOrderAnswer.builder().id(dto.questionOrderAnswerId()).build())
                .questionOrderOptions(QuestionOrderOptions.builder().id(dto.questionOrderOptionsId()).build())
                .place(dto.place())
                .build();
        return questionOrderOptionsAnswerRepository.save(questionOrderOptionsAnswer);
    }

    public QuestionOrderOptionsAnswer updateQuestionOrderOptionsAnswer(@NotNull QuestionOrderOptionsAnswerUpdateDTO dto) {
        return questionOrderOptionsAnswerRepository.findById(dto.questionOrderOptionsAnswerId())
                .map(q -> {
                    if (dto.questionOrderAnswerId() != null)
                        q.setQuestionOrderAnswer(QuestionOrderAnswer.builder().id(dto.questionOrderAnswerId()).build());
                    if (dto.questionOrderOptionsId() != null)
                        q.setQuestionOrderOptions(QuestionOrderOptions.builder().id(dto.questionOrderOptionsId()).build());
                    if (dto.place() != null)
                        q.setPlace(dto.place());
                    return questionOrderOptionsAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderOptionsAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuestionOrderOptionsAnswer deleteQuestionOrderOptionsAnswer(UUID id) {
        return questionOrderOptionsAnswerRepository.findById(id)
                .map(q -> {
                    questionOrderOptionsAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderOptionsAnswer not found", HttpStatus.NOT_FOUND));
    }
}
