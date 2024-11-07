package com.learningpulse.quiz.question.question_text.service;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question.question_text.dto.question_text.QuestionTextCreateDTO;
import com.learningpulse.quiz.question.question_text.dto.question_text.QuestionTextUpdateDTO;
import com.learningpulse.quiz.question.question_text.model.QuestionText;
import com.learningpulse.quiz.question.question_text.model.QuestionTextAnswerRepository;
import com.learningpulse.quiz.question.question_text.model.QuestionTextRepository;
import com.learningpulse.quiz.quiz.Quiz;
import jakarta.transaction.Transactional;
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
    private final QuestionTextAnswerRepository questionTextAnswerRepository;

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

    public QuestionText createQuestionText(UUID sub, @NotNull QuestionTextCreateDTO questionTextCreateDTO) {
        QuestionText questionText = QuestionText.builder()
                .title(questionTextCreateDTO.title())
                .answer(questionTextCreateDTO.answer())
                .createdBy(sub)
                .quiz(Quiz.builder()
                        .id(questionTextCreateDTO.quizId())
                        .build())
                .build();
        return questionTextRepository.save(questionText);
    }

    // This may cause inconsistencies in the database
    @Transactional
    public QuestionText updateQuestionText(@NotNull QuestionTextUpdateDTO questionTextUpdateDTO) {
        QuestionText questionText = questionTextRepository.findById(questionTextUpdateDTO.id()).orElseThrow(() -> new HttpStatusCodeException("QuestionText not found", HttpStatus.NOT_FOUND));

        questionText.setTitle(questionTextUpdateDTO.title());
        questionText.setAnswer(questionTextUpdateDTO.answer());
        questionText.setQuiz(Quiz.builder()
                .id(questionTextUpdateDTO.quizId())
                .build());

        if (questionTextUpdateDTO.answerIds().isEmpty()) {
            return questionTextRepository.save(questionText);
        }

        questionTextAnswerRepository.deleteAllByBelongsToId(questionText.getId());
        questionTextAnswerRepository.findAllById(questionTextUpdateDTO.answerIds()).forEach(questionTextAnswer -> {
            questionTextAnswer.setBelongsTo(questionText);
            questionTextAnswerRepository.save(questionTextAnswer);
        });

        return questionTextRepository.save(questionText);
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
