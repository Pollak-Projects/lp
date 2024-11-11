package com.learningpulse.quiz.question_answer.question_checkbox_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckbox;
import com.learningpulse.quiz.question.question_checkbox.model.QuestionCheckboxOptions;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_options_answer.QuestionCheckboxOptionsAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.dto.question_checkbox_options_answer.QuestionCheckboxOptionsAnswerUpdateDTO;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.model.QuestionCheckboxOptionsAnswer;
import com.learningpulse.quiz.question_answer.question_checkbox_answer.repository.QuestionCheckboxOptionsAnswerRepository;
import com.learningpulse.quiz.quiz_answer.QuizAnswer;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionCheckboxOptionsAnswerService {
    private final QuestionCheckboxOptionsAnswerRepository QuestionCheckboxOptionsAnswerRepository;


    public QuestionCheckboxOptionsAnswer getQuestionCheckboxOptionsAnswerById(UUID id) {
        return QuestionCheckboxOptionsAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxOptionsAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionCheckboxOptionsAnswer> getAllQuestionCheckboxOptionsAnswerByUser(UUID sub) {
        List<QuestionCheckboxOptionsAnswer> QuestionCheckboxOptionsAnswers = QuestionCheckboxOptionsAnswerRepository.findAllByCreatedBy(sub);
        if (QuestionCheckboxOptionsAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxOptionsAnswer not found", HttpStatus.NOT_FOUND);
        return QuestionCheckboxOptionsAnswers;
    }

    public List<QuestionCheckboxOptionsAnswer> getAllQuestionCheckboxOptionsAnswer() {
        List<QuestionCheckboxOptionsAnswer> QuestionCheckboxOptionsAnswers = QuestionCheckboxOptionsAnswerRepository.findAll();
        if (QuestionCheckboxOptionsAnswers.isEmpty())
            throw new HttpStatusCodeException("QuestionCheckboxOptionsAnswer not found", HttpStatus.NOT_FOUND);
        return QuestionCheckboxOptionsAnswers;
    }

    public QuestionCheckboxOptionsAnswer createQuestionCheckboxOptionsAnswer(UUID sub, @NotNull QuestionCheckboxOptionsAnswerCreateDTO dto) {
        QuestionCheckboxOptionsAnswer questionCheckboxOptionsAnswer = QuestionCheckboxOptionsAnswer.builder()
                .createdBy(sub)
                .questionCheckboxAnswer(QuestionCheckboxAnswer.builder().id(dto.questionCheckboxAnswerId()).build())
                .questionCheckboxOptions(QuestionCheckboxOptions.builder().id(dto.questionCheckboxOptionsId()).build())
                .answer(dto.answer())
                .build();

        return QuestionCheckboxOptionsAnswerRepository.save(questionCheckboxOptionsAnswer);
    }

    public QuestionCheckboxOptionsAnswer updateQuestionCheckboxOptionsAnswer(@NotNull QuestionCheckboxOptionsAnswerUpdateDTO dto) {
        return QuestionCheckboxOptionsAnswerRepository.findById(dto.questionCheckboxOptionsAnswerId())
                .map(q -> {
                    if (dto.questionCheckboxAnswerId() != null)
                        q.setQuestionCheckboxAnswer(QuestionCheckboxAnswer.builder().id(dto.questionCheckboxAnswerId()).build());
                    if (dto.questionCheckboxOptionsId() != null)
                        q.setQuestionCheckboxOptions(QuestionCheckboxOptions.builder().id(dto.questionCheckboxOptionsId()).build());
                    if (dto.answer() != null)
                        q.setAnswer(dto.answer());
                    return QuestionCheckboxOptionsAnswerRepository.save(q);
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxOptionsAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuestionCheckboxOptionsAnswer deleteQuestionCheckboxOptionsAnswer(UUID id) {
        return QuestionCheckboxOptionsAnswerRepository.findById(id)
                .map(q -> {
                    QuestionCheckboxOptionsAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionCheckboxOptionsAnswer not found", HttpStatus.NOT_FOUND));
    }
}
