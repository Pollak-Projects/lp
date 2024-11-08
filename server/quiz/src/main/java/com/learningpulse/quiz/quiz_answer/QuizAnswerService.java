package com.learningpulse.quiz.quiz_answer;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizAnswerService {
    private final QuizAnswerRepository quizAnswerRepository;

    public QuizAnswer getQuizAnswerById(UUID id) {
        return quizAnswerRepository.findById(id)
                .orElseThrow(() -> new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND));
    }

    public List<QuizAnswer> getAllQuizAnswersByUser(UUID sub) {
        List<QuizAnswer> quizAnswers = quizAnswerRepository.findAllByCreatedBy(sub);
        if (quizAnswers.isEmpty())
            throw new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND);
        return quizAnswers;
    }

    public List<QuizAnswer> getAllQuizAnswers() {
        List<QuizAnswer> quizAnswers = quizAnswerRepository.findAll();
        if (quizAnswers.isEmpty())
            throw new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND);
        return quizAnswers;
    }

    public QuizAnswer createQuizAnswer(UUID sub, @NotNull QuizAnswer quizAnswer) {
        quizAnswer.setCreatedBy(sub);
        return quizAnswerRepository.save(quizAnswer);
    }

    // TODO add DTO
    public QuizAnswer updateQuizAnswer(@NotNull QuizAnswer quizAnswer) {
        return quizAnswerRepository.findById(quizAnswer.getId())
                .map(q -> quizAnswerRepository.save(quizAnswer))
                .orElseThrow(() -> new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND));
    }

    public QuizAnswer deleteQuizAnswer(UUID id) {
        return quizAnswerRepository.findById(id)
                .map(q -> {
                    quizAnswerRepository.delete(q);
                    return q;
                })
                .orElseThrow(() -> new HttpStatusCodeException("QuizAnswer not found", HttpStatus.NOT_FOUND));
    }
}
