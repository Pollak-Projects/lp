package com.learningpulse.quiz.quiz_answer;

import com.learningpulse.quiz.exception.HttpStatusCodeException;
import com.learningpulse.quiz.question_answer.question_text_answer.model.QuestionTextAnswer;
import com.learningpulse.quiz.quiz.Quiz;
import com.learningpulse.quiz.quiz_answer.dto.QuizAnswerCreateDTO;
import com.learningpulse.quiz.quiz_answer.dto.QuizAnswerUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public QuizAnswer createQuizAnswer(UUID sub, @NotNull QuizAnswerCreateDTO quizAnswerCreateDTO) {
        QuizAnswer quizAnswer = QuizAnswer.builder()
                .createdBy(sub)
                .quiz(Quiz.builder().id(quizAnswerCreateDTO.quizId()).build())
                .build();

        quizAnswer.setQuestionTextAnswers(quizAnswerCreateDTO.questionTextAnswers().stream().map(q -> QuestionTextAnswer.builder()
                .answer(q.answer())
                .createdBy(sub)
                .belongsTo(quizAnswer)
                .build()).collect(Collectors.toSet()));
        return quizAnswerRepository.save(quizAnswer);
    }

    public QuizAnswer updateQuizAnswer(@NotNull QuizAnswerUpdateDTO quizAnswerUpdateDTO) {
        return quizAnswerRepository.findById(quizAnswerUpdateDTO.quizAnswerId())
                .map(q -> {
                    q.setQuiz(Quiz.builder().id(quizAnswerUpdateDTO.quizId()).build());
                    q.setQuestionTextAnswers(quizAnswerUpdateDTO.questionTextAnswers().stream().map(qta -> QuestionTextAnswer.builder()
                            .answer(qta.answer())
                            .createdBy(quizAnswerUpdateDTO.createdBy())
                            .belongsTo(q)
                            .build()).collect(Collectors.toSet()));
                    return quizAnswerRepository.save(q);
                })
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
