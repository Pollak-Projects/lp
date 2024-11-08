package com.learningpulse.quiz.question_answer.question_text_answer.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.question_answer.question_text_answer.dto.question_text_answer.QuestionTextAnswerCreateDTO;
import com.learningpulse.quiz.question_answer.question_text_answer.model.QuestionTextAnswer;
import com.learningpulse.quiz.question_answer.question_text_answer.model.QuestionTextAnswerRepository;
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

    public QuestionTextAnswer createQuestionTextAnswer(UUID sub, @NotNull QuestionTextAnswerCreateDTO questionTextAnswerDTO) {
        QuestionTextAnswer questionTextAnswer = QuestionTextAnswer.builder()
                .answer(questionTextAnswerDTO.answer())
                .createdBy(sub)
                .belongsTo(QuestionText.builder().id(questionTextAnswerDTO.belongsToId()).build())
                .build();
        return questionTextAnswerRepository.save(questionTextAnswer);
    }

    public QuestionTextAnswer updateQuestionTextAnswer(@NotNull QuestionTextAnswer questionTextAnswer) {
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
