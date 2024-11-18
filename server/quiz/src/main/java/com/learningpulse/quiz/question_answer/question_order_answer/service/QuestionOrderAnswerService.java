package com.learningpulse.quiz.question_answer.question_order_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_order.model.QuestionOrder;
import com.learningpulse.quiz.question.question_order.model.QuestionOrderOptions;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_answer.QuestionOrderAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.dto.question_order_answer.QuestionOrderAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.model.QuestionOrderOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_order_answer.repository.QuestionOrderAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
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
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NO_CONTENT));
    }

    public List<QuestionOrderAnswer> getAllQuestionOrderAnswer() {
        List<QuestionOrderAnswer> questionOrderAnswers = questionOrderAnswerRepository.findAll();
        if (questionOrderAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NO_CONTENT);
        return questionOrderAnswers;
    }

    public List<QuestionOrderAnswer> getAllQuestionOrderAnswerByUser(UUID sub) {
        List<QuestionOrderAnswer> questionOrderAnswers = questionOrderAnswerRepository.findAllByCreatedBy(sub);
        if (questionOrderAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NO_CONTENT);
        return questionOrderAnswers;
    }

    public QuestionOrderAnswer createQuestionOrderAnswer(UUID sub, @NotNull QuestionOrderAnswerCreateDTO dto) {
        QuestionOrderAnswer questionOrderAnswer = QuestionOrderAnswer.builder()
                .createdBy(sub)
                .belongsTo(QuizAnswer.builder().id(dto.questionAnswerId()).build())
                .questionOrder(QuestionOrder.builder().id(dto.questionOrderId()).build())
                .build();

        questionOrderAnswer.setOptions(dto.options().stream().map(o -> QuestionOrderOptionsAnswer.builder()
                .createdBy(sub)
                .questionOrderAnswer(questionOrderAnswer)
                .questionOrderOptions(QuestionOrderOptions.builder()
                        .id(o.questionOrderOptionsId())
                        .build())
                .place(o.place())
                .build()).toList());

        return questionOrderAnswerRepository.save(questionOrderAnswer);
    }

    public QuestionOrderAnswer updateQuestionOrderAnswer(@NotNull QuestionOrderAnswerUpdateDTO dto) {
        return questionOrderAnswerRepository.findById(dto.questionOrderAnswerId())
                .map(q -> {
                    if (dto.questionAnswerId() != null)
                        q.setBelongsTo(QuizAnswer.builder().id(dto.questionAnswerId()).build());
                    if (dto.questionOrderId() != null)
                        q.setQuestionOrder(QuestionOrder.builder().id(dto.questionOrderId()).build());
                    if (dto.options() != null && !dto.options().isEmpty())
                        q.setOptions(dto.options().stream().map(o -> QuestionOrderOptionsAnswer.builder()
                                .id(o)
                                .build()).toList());
                    return questionOrderAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NO_CONTENT));
    }

    public QuestionOrderAnswer deleteQuestionOrderAnswer(UUID id) {
        return questionOrderAnswerRepository.findById(id)
                .map(q -> {
                    questionOrderAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionOrderAnswer not found", HttpStatus.NO_CONTENT));
    }
}
