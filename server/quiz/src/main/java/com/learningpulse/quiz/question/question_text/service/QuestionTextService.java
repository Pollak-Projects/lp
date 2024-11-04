package com.learningpulse.quiz.question.question_text.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.question.question_text.model.QuestionTextRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionTextService {
    private final QuestionTextRepository questionTextRepository;

    public QuestionText getQuestionTextById(UUID id) {
        return questionTextRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuestionText not found", HttpStatus.NOT_FOUND));
    }

    public List<QuestionText> getAllQuestionTextsByUser(UUID sub) {
        List<QuestionText> questionTexts = questionTextRepository.findAllByCreatedBy(sub);
        if (questionTexts.isEmpty())
            throw new HttpStatusCodeException("QuestionText not found", HttpStatus.NOT_FOUND);
        return questionTexts;
    }

    public List<QuestionText> getAllQuestionTexts() {
        List<QuestionText> questionTexts = questionTextRepository.findAll();
        if (questionTexts.isEmpty())
            throw new HttpStatusCodeException("QuestionText not found", HttpStatus.NOT_FOUND);
        return questionTexts;
    }

    public QuestionText createQuestionText(UUID sub, @NotNull QuestionText questionText) {
        questionText.setCreatedBy(sub);
        return questionTextRepository.save(questionText);
    }

    public QuestionText updateQuestionText(@NotNull QuestionText questionText) {
        return questionTextRepository.findById(questionText.getId())
                .map(q -> questionTextRepository.save(questionText))
                .orElseThrow(() -> new HttpStatusCodeException("QuestionText not found", HttpStatus.NOT_FOUND));
    }

    public QuestionText deleteQuestionText(UUID id) {
        return questionTextRepository.findById(id)
                .map(q -> {
                    questionTextRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuestionText not found", HttpStatus.NOT_FOUND));

    }
}
