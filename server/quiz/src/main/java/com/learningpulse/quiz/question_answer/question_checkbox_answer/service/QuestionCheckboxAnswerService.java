package com.learningpulse.quiz.question_answer.question_checkbox_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_answer.QuestionCheckboxAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_answer.QuestionCheckboxAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.repository.QuestionCheckboxAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
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
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NO_CONTENT));
    }

    public List<QuestionCheckboxAnswer> getAllQuestionCheckboxAnswerByUser(UUID sub) {
        List<QuestionCheckboxAnswer> questionCheckboxAnswers = questionCheckboxAnswerRepository.findAllByCreatedBy(sub);
        if (questionCheckboxAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NO_CONTENT);
        return questionCheckboxAnswers;
    }

    public List<QuestionCheckboxAnswer> getAllQuestionCheckboxAnswer() {
        List<QuestionCheckboxAnswer> questionCheckboxAnswers = questionCheckboxAnswerRepository.findAll();
        if (questionCheckboxAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NO_CONTENT);
        return questionCheckboxAnswers;
    }

    public QuestionCheckboxAnswer createQuestionCheckboxAnswer(UUID sub, @NotNull QuestionCheckboxAnswerCreateDTO dto) {
        QuestionCheckboxAnswer questionCheckboxAnswer = QuestionCheckboxAnswer.builder()
                .createdBy(sub)
                .belongsTo(QuizAnswer.builder().id(dto.quizAnswerId()).build())
                .questionCheckbox(QuestionCheckbox.builder().id(dto.questionCheckboxId()).build())
                .build();

        questionCheckboxAnswer.setOptions(dto.options().stream().map(o -> QuestionCheckboxOptionsAnswer.builder()
                .questionCheckboxAnswer(questionCheckboxAnswer)
                .questionCheckboxOptions(QuestionCheckboxOptions.builder().id(o.questionCheckboxOptionsId()).build())
                .answer(o.answer())
                .build()).toList());

        return questionCheckboxAnswerRepository.save(questionCheckboxAnswer);
    }

    public QuestionCheckboxAnswer updateQuestionCheckboxAnswer(@NotNull QuestionCheckboxAnswerUpdateDTO dto) {
        return questionCheckboxAnswerRepository.findById(dto.questionCheckboxAnswerId())
                .map(q -> {
                    if (dto.quizAnswerId() != null)
                        q.setBelongsTo(QuizAnswer.builder().id(dto.quizAnswerId()).build());
                    if (dto.questionCheckboxId() != null)
                        q.setQuestionCheckbox(QuestionCheckbox.builder().id(dto.questionCheckboxId()).build());
                    if (dto.options() != null)
                        q.setOptions(dto.options().stream().map(o -> QuestionCheckboxOptionsAnswer.builder()
                                .id(o)
                                .build()).toList());
                    return questionCheckboxAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NO_CONTENT));
    }

    public QuestionCheckboxAnswer deleteQuestionCheckboxAnswer(UUID id) {
        return questionCheckboxAnswerRepository.findById(id)
                .map(q -> {
                    questionCheckboxAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxAnswer not found", HttpStatus.NO_CONTENT));
    }
}
